/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entidades.Permisos;
import facades.PermisosFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author german
 */
@Named(value = "permisosConverter")
@ViewScoped
@FacesConverter("permisosConverter")
public class PermisosConverter implements Converter, Serializable{

    @EJB
    private PermisosFacade permisosFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
    {
        try
        {
            return permisosFacade.findById(Integer.parseInt(string));
        }
        catch (Exception ex)
        {
            System.err.print(ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj)
    {
        if(obj instanceof Permisos)
        {
            Permisos ut = (Permisos)obj;
            return ut.getIdPermiso().toString(); //ut.getIdObjeto().getNombre()+"-"+ut.getIdOperacion().getNombre();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("El objeto no es ");
            sbError.append(obj.getClass().getName()).append(" Permisos");
            throw new ClassCastException(sbError.toString());
        }
    }
  }