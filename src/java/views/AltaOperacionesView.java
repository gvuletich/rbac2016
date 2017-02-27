/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Operaciones;
import facades.OperacionesFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.transaction.UserTransaction;

/**
 *
 * @author german
 */
@ManagedBean(name = "altaOperacionesView")
@ViewScoped
public class AltaOperacionesView implements Serializable {
    
    @EJB
    private OperacionesFacade operacionesFacade;   
    private Operaciones operacion;

    @Resource
    UserTransaction tx;
    
    /**
     * Creates a new instance of AltaOperacionesView
     */
//    public AltaOperacionesView() {
//        
//    }
    
    private void reset(){    
        operacion=null;
    }
    
    @PostConstruct
    public void init(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")!=null){
            operacion = operacionesFacade.findById(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
            if(operacion!=null){
                preparaEditar();
            }else
                ;//redirect to somewhere
            }
        else{
            preparaCrear();
        }
    }
    
    public void nuevoOperacion(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_operaciones.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }
    
    public void preparaCrear(){
        operacion = new Operaciones();
        //crear();
    }
    
    public void preparaEditar(){
        // falta implementar
    }
    
    public String crear(){

//        operacion.setIdTipo(tipo);
//        operacion.setNombre(nombre);
//        operacion.setPassword(password);
        operacion.setHabilitado(true);
        try{
            operacionesFacade.edit(operacion);
        
        } catch(Exception e){
            
            System.out.println(e);
        }
        return "/faces/altas/operaciones.xhtml?faces-redirect=true";
        
    }
    
    public String eliminar(){
        operacionesFacade.remove(operacion);
        //operacion.setHabilitado(false);
        //operacionesFacade.edit(operacion);
        reset();
        return "/faces/altas/operaciones.xhtml?faces-redirect=true";
    }
    
    public Operaciones getOperacion() {
        return operacion;
    }

    public void setOperacion(Operaciones operacion) {
        this.operacion = operacion;
    }

}
