/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Permisos;
import facades.PermisosFacade;
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
@Named(value = "permisosView")
@ViewScoped
public class PermisosView implements Serializable {
    
    @EJB
    private PermisosFacade permisosFacade;   
    private Permisos permiso;
    
    private List<Permisos> permisos=null;
    
    public void onRowSelect(SelectEvent event) {
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_permisos.xhtml?faces-redirect=true");
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }

    public List<Permisos> getPermisos() {
        if(permisos==null)
            permisos = permisosFacade.findByHabilitado(true);
        return permisos;
    }

    public void setPermisos(List<Permisos> permisos) {
        this.permisos = permisos;
    }
    
    
    
    
}
