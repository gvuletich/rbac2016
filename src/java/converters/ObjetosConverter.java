/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entidades.Objetos;
import facades.ObjetosFacade;
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
@Named(value = "objetosConverter")
@ViewScoped
@FacesConverter("objetosConverter")
public class ObjetosConverter implements Converter, Serializable{

    @EJB
    private ObjetosFacade objetosFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
    {
        try
        {
            return objetosFacade.findByNombre(string);
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
        if(obj instanceof Objetos)
        {
            Objetos ut = (Objetos)obj;
            return ut.getNombre();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("El objeto no es ");
            sbError.append(obj.getClass().getName()).append(" Objetos");
            throw new ClassCastException(sbError.toString());
        }
    }
  }