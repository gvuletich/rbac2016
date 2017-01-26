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
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author german
 */
@Named(value = "altaCajasView")
@ViewScoped
public class AltaCajasView implements Serializable {
    
    @EJB
    private CajasFacade cajasFacade;   
    private Cajas caja;
    
    @EJB
    private CajastipoFacade cajastipoFacade;   
    private List<Cajastipo> cajastipos;
    
    private Cajastipo tipo;
    private String msg;
    
//    @ManagedProperty(value = "#{loginView}")
//    private loginView loginview;  
    
    /**
     * Creates a new instance of AltaCajasView
     */
//    public AltaCajasView() {
//        
//    }
    public void crear(){
        caja = new Cajas();
        caja.setIdTipo(tipo);
        caja.setMsg(msg);
        caja.setHabilitado(true);
        cajasFacade.edit(caja);
    }
    
    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public Cajastipo getTipo() {
        return tipo;
    }

    public void setTipo(Cajastipo tipo) {
        this.tipo = tipo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public List<Cajastipo> getCajastipos() {
        if (cajastipos==null)
            cajastipos=cajastipoFacade.getAll();
            
        return cajastipos;
    }

    public void setCajastipos(List<Cajastipo> cajastipos) {
        this.cajastipos = cajastipos;
    }
    
}
