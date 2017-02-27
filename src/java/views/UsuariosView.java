/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Usuarios;
import facades.UsuariosFacade;
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
 * @author german
 */
@Named(value = "usuariosView")
@ViewScoped
public class UsuariosView implements Serializable {
    
    @EJB
    private UsuariosFacade usuariosFacade;   
    private Usuarios usuario;
    
    private List<Usuarios> usuarios=null;
    
    public void onRowSelect(SelectEvent event) {
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_usuario.xhtml?faces-redirect=true");
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Usuarios> getUsuarios() {
        if(usuarios==null)
            usuarios = usuariosFacade.findByHabilitado(true);
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    
}
