/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import entidades.Roles;
import facades.RolesFacade;
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
@Named(value = "rolesConverter")
@ViewScoped
@FacesConverter("rolesConverter")
public class RolesConverter implements Converter, Serializable{

    @EJB
    private RolesFacade rolesFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string)
    {
        try
        {
            return rolesFacade.findByNombre((string));
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
        if(obj instanceof Roles)
        {
            Roles ut = (Roles)obj;
            return ut.getNombre();
        }
        else
        {
            StringBuilder sbError = new StringBuilder("El rol no es ");
            sbError.append(obj.getClass().getName()).append(" Roles");
            throw new ClassCastException(sbError.toString());
        }
    }
  }