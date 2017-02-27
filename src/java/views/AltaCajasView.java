/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Cajas;
import entidades.Cajastipo;
import entidades.Usuarios;
import facades.CajasFacade;
import facades.CajastipoFacade;
import facades.UsuariosFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.transaction.UserTransaction;

/**
 *
 * @author german
 */
@ManagedBean(name = "altaCajasView")
@ViewScoped
public class AltaCajasView implements Serializable {
    
    @EJB
    private CajasFacade cajasFacade;   
    private Cajas caja=null;
    
    @EJB
    private CajastipoFacade cajastipoFacade;   
    private List<Cajastipo> cajatipos;
    
    @EJB
    private UsuariosFacade usuariosFacade;
    
    @ManagedProperty(value="#{loginView}")
    private loginView loginv;

    public loginView getLoginv() {
        return loginv;
    }

    public void setLoginv(loginView loginv) {
        this.loginv = loginv;
    }
    
    
    public List<Cajastipo> getCajatipos() {
        if(cajatipos==null)
            cajatipos = cajastipoFacade.getAll();
        return cajatipos;
    }

    public void setCajatipos(List<Cajastipo> cajatipos) {
        this.cajatipos = cajatipos;
    }
        
    private List<Usuarios> usuarios;

    
    @Resource
    UserTransaction tx;
    
    
    private void reset(){    
        caja=null;
    }
    
    @PostConstruct
    public void init(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")!=null){
            caja = cajasFacade.findById(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
            System.out.println("ATENCIONNNNNN: en Init leo loginUsuario:"+loginv.getUsuario()+" con idUsuario: "+usuariosFacade.findByNombre(loginv.getUsuario()).getIdUsuario());
            if(caja!=null){
                preparaEditar();
            }else
                ;//redirect to somewhere
            }
        else{
            preparaCrear();
        }
    }

    public void nuevoCaja(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_cajas.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }
    
    public void preparaCrear(){
        caja = new Cajas();
        //crear();
    }
    
    public void preparaEditar(){
        ;// falta implementar
    }
    
    public String crear(){

//        caja.setIdTipo(tipo);
//        caja.setNombre(nombre);
//        caja.setPassword(password);
        caja.setHabilitado(true);
        caja.setIdUsuario(usuariosFacade.findByNombre(loginv.getUsuario()));
        try{
            cajasFacade.edit(caja);
        
        } catch(Exception e){
            
            System.out.println(e);
        }
        return "/faces/altas/cajas.xhtml?faces-redirect=true";
    }
    
    public String eliminar(){
        cajasFacade.remove(caja);
        //caja.setHabilitado(false);
        //cajasFacade.edit(caja);
        reset();
        return "/faces/altas/cajas.xhtml?faces-redirect=true";
    }
    
    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }
    
    public List<Usuarios> getUsuarios() {
        if(usuarios==null)
            usuarios = usuariosFacade.findAll();
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    

   
}
