/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Objetos;
import facades.ObjetosFacade;
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
@ManagedBean(name = "altaObjetosView")
@ViewScoped
public class AltaObjetosView implements Serializable {
    
    @EJB
    private ObjetosFacade objetosFacade;   
    private Objetos objeto;

    @Resource
    UserTransaction tx;
    
    /**
     * Creates a new instance of AltaObjetosView
     */
//    public AltaObjetosView() {
//        
//    }
    
    private void reset(){    
        objeto=null;
    }
    
    @PostConstruct
    public void init(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")!=null){
            objeto = objetosFacade.findById(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
            if(objeto!=null){
                preparaEditar();
            }else
                ;//redirect to somewhere
            }
        else{
            preparaCrear();
        }
    }
    
    public void nuevoObjeto(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_objetos.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }
    
    public void preparaCrear(){
        objeto = new Objetos();
        //crear();
    }
    
    public void preparaEditar(){
        // falta implementar
    }
    
    public String crear(){

//        objeto.setIdTipo(tipo);
//        objeto.setNombre(nombre);
//        objeto.setPassword(password);
        objeto.setHabilitado(true);
        try{
            objetosFacade.edit(objeto);
        
        } catch(Exception e){
            
            System.out.println(e);
        }
        return "/faces/altas/objetos.xhtml?faces-redirect=true";
    }
    
    public String eliminar(){
        objetosFacade.remove(objeto);
        //objeto.setHabilitado(false);
        //objetosFacade.edit(objeto);
        reset();
        return "/faces/altas/objetos.xhtml?faces-redirect=true";
    }
    
    public Objetos getObjeto() {
        return objeto;
    }

    public void setObjeto(Objetos objeto) {
        this.objeto = objeto;
    }

}
