/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Roles;
import facades.RolesFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author germanU
 */
@Named(value = "rolesView")
@ViewScoped
public class RolesView implements Serializable {
    
    @EJB
    private RolesFacade rolesFacade;   
    private Roles rol;
    
    private List<Roles> roles=null;
    
    public void onRowSelect(SelectEvent event) {
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_roles.xhtml?faces-redirect=true");
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Roles> getRoles() {
        if(roles==null)
            roles = rolesFacade.findByHabilitado(true);
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
    
    
    
    
}
