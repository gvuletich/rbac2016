/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Objetos;
import entidades.Operaciones;
import entidades.Permisos;
import facades.ObjetosFacade;
import facades.OperacionesFacade;
import facades.PermisosFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
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
@ManagedBean(name = "altaPermisosView")
@ViewScoped
public class AltaPermisosView implements Serializable {
    
    @EJB
    private PermisosFacade permisosFacade;   
    private Permisos permiso;
    
    @EJB
    private ObjetosFacade objetosFacade; 
    private List<Objetos> objetos;
    
    @EJB
    private OperacionesFacade operacionesFacade; 
    private List<Operaciones> operaciones;

    public List<Objetos> getObjetos() {
        if(objetos==null)
            objetos = objetosFacade.findAll();
        return objetos;
    }

    public void setObjetos(List<Objetos> objetos) {
        this.objetos = objetos;
    }

    public List<Operaciones> getOperaciones() {
        if(operaciones==null)
            operaciones = operacionesFacade.findAll();
        return operaciones;
    }

    public void setOperaciones(List<Operaciones> operaciones) {
        this.operaciones = operaciones;
    }
 
    
        
    @Resource
    UserTransaction tx;
    
    /**
     * Creates a new instance of AltaPermisosView
     */
//    public AltaPermisosView() {
//        
//    }
    
    private void reset(){    
        permiso=null;
    }
    
    @PostConstruct
    public void init(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")!=null){
            permiso = permisosFacade.findById(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
            if(permiso!=null){
                preparaEditar();
            }else
                ;//redirect to somewhere
            }
        else{
            preparaCrear();
        }
    }
    
    public void nuevoPermiso(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_permisos.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }
    
    public void preparaCrear(){
        permiso = new Permisos();
        //crear();
    }
    
    public void preparaEditar(){
        // falta implementar
    }
    
    public String crear(){

//        permiso.setIdTipo(tipo);
//        permiso.setNombre(nombre);
//        permiso.setPassword(password);
        permiso.setHabilitado(true);
        try{
            permisosFacade.edit(permiso);
        
        } catch(Exception e){
            
            System.out.println(e);
        }
        return "/faces/altas/permisos.xhtml?faces-redirect=true";
        
    }
    
    public String eliminar(){
        permisosFacade.remove(permiso);
        //permiso.setHabilitado(false);
        //permisosFacade.edit(permiso);
        reset();
        return "/faces/altas/permisos.xhtml?faces-redirect=true";
    }
    
    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }
    
}
