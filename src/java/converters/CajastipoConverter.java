/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entidades.Cajastipo;
import facades.CajastipoFacade;
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
@Named(value = "cajastipoConverter")
@ViewScoped
@FacesConverter("cajastipoConverter")
public class CajastipoConverter implements Converter, Serializable{

    @EJB
    private CajastipoFacade cajastipoFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
    {
        try
        {
            return cajastipoFacade.findByNombre(string);
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
        if(obj instanceof Cajastipo)
        {
            Cajastipo ut = (Cajastipo)obj;
            return ut.getNombre();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("El objeto no es ");
            sbError.append(obj.getClass().getName()).append(" Cajastipo");
            throw new ClassCastException(sbError.toString());
        }
    }
  }