/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entidades.Usuariotipo;
import facades.UsuariotipoFacade;
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
@Named(value = "usuariotipoConverter")
@ViewScoped
@FacesConverter("usuariotipoConverter")
public class UsuariotipoConverter implements Converter, Serializable{

    @EJB
    private UsuariotipoFacade usuariotipoFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
    {
        try
        {
            return usuariotipoFacade.findByNombre(string);
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
        if(obj instanceof Usuariotipo)
        {
            Usuariotipo ut = (Usuariotipo)obj;
            return ut.getNombre();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("El objeto no es ");
            sbError.append(obj.getClass().getName()).append(" Usuariotipo");
            throw new ClassCastException(sbError.toString());
        }
    }
  }