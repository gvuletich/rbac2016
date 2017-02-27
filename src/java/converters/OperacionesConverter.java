/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entidades.Operaciones;
import facades.OperacionesFacade;
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
@Named(value = "operacionesConverter")
@ViewScoped
@FacesConverter("operacionesConverter")
public class OperacionesConverter implements Converter, Serializable{

    @EJB
    private OperacionesFacade operacionesFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
    {
        try
        {
            return operacionesFacade.findByNombre(string);
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
        if(obj instanceof Operaciones)
        {
            Operaciones ut = (Operaciones)obj;
            return ut.getNombre();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("El objeto no es ");
            sbError.append(obj.getClass().getName()).append(" Operaciones");
            throw new ClassCastException(sbError.toString());
        }
    }
  }