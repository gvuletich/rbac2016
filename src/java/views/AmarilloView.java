/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Cajas;
import entidades.Cajastipo;
import facades.CajasFacade;
import facades.CajastipoFacade;
import facades.UsuariosFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

/**
 *
 * @author german
 */
@ManagedBean(name = "amarilloView")
@ViewScoped
public class AmarilloView implements Serializable {

    public loginView getLoginView() {
        return loginView;
    }

    public void setLoginView(loginView loginView) {
        this.loginView = loginView;
    }
    
    @EJB
    private CajasFacade cajasFacade;   
    private Cajas caja;
    private List<Cajas> cajas;
    
    @EJB
    private UsuariosFacade usuariosFacade;   
    
    @EJB
    private CajastipoFacade cajastipoFacade;   
    private List<Cajastipo> cajastipos;
        
    @ManagedProperty(value = "#{loginView}")
    private loginView loginView;    

    public List<Cajas> getCajas() {
        if (cajas==null)
            cajas = cajasFacade.findByUsuario(loginView.getUser());
        return cajas;
    }

    public void setCajas(List<Cajas> cajas) {
        this.cajas = cajas;
    }
    
    

    
}
