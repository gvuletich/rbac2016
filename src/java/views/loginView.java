/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Usuarios;
import facades.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author german
 */
@Named(value = "loginView")
@SessionScoped
public class loginView implements Serializable {

    /**
     * Creates a new instance of loginView
     */
    
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String password;
    boolean loggedIn = false;
    
    @EJB 
    private UsuariosFacade usuariosFacade;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public loginView() {
    }
    
    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        //FacesMessage message = null;
        
        Usuarios user=null;
        if (getUsuario()!=null)
             user = usuariosFacade.findByNombre(getUsuario());
        if(getUsuario() != null && getUsuario().equals(user.getNombre()) && getPassword() != null && getPassword().equals(user.getPassword())) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usuario);
            loggedIn = true;
            //message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", usuario);
            return "index.xhtml?faces-redirect=true";
        } else {
            loggedIn = false;
            //message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        return "login.xhtml?faces-redirect=true";
    }
    
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        loggedIn = false;
        return "login.xhtml?faces-redirect=true";
    }
    
}
