/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Usuarios;
import facades.UsuariosFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author german
 */
//@Named(value = "loginView")
@ManagedBean(name = "loginView")
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
    private Usuarios user;

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

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }
    
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        loggedIn = false;
        return "/faces/login.xhtml?faces-redirect=true";
    }
    
}
