/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Permisos;
import entidades.Roles;
import facades.PermisosFacade;
import facades.RolesFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "altaRolesView")
@ViewScoped
public class AltaRolesView implements Serializable {
    
    @EJB
    private RolesFacade rolesFacade;   
    private Roles rol;
        
    @EJB
    private PermisosFacade permisosFacade;   
    private List<Permisos> permisos;
    private List<Permisos> permisosSelect;    
    private Permisos permiso;
    
    @Resource
    UserTransaction tx;
    
    /**
     * Creates a new instance of AltaRolesView
     */
//    public AltaRolesView() {
//        
//    }
    
    private void reset(){    
        rol=null;
        permisos=null;
        permisosSelect=null;

    }
    
    @PostConstruct
    public void init(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")!=null){
            rol = rolesFacade.findById(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
            if(rol!=null){
                getPermisosSelect();
                preparaEditar();
            }else
                ;//redirect to somewhere
            }
        else{
            preparaCrear();
        }
    }
    
    public void nuevoRol(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_roles.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }
    
    public void preparaCrear(){
        rol = new Roles();
        //crear();
    }
    
    public void preparaEditar(){
        // falta implementar
    }
    
    public String crear(){

//        rol.setIdTipo(tipo);
//        rol.setNombre(nombre);
//        rol.setPassword(password);
        rol.setHabilitado(true);
        try{
            //rol.setPermisosCollection(permisosSelect);
            rolesFacade.edit(rol);
        
        } catch(Exception e){
            
            System.out.println(e);
        }
        return "/faces/altas/roles.xhtml?faces-redirect=true";
        
    }
    
    public String eliminar(){
        rolesFacade.remove(rol);
        //rol.setHabilitado(false);
        //rolesFacade.edit(rol);
        reset();
        return "/faces/altas/roles.xhtml?faces-redirect=true";
    }
    
    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    
    public List<Permisos> getPermisos() {
        if (permisos==null)
            permisos = permisosFacade.findAll();
        return permisos;
    }

    public void setPermisos(List<Permisos> permisos) {
        this.permisos = permisos;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }
    
    public List<Permisos> getPermisosSelect() {
        if(permisosSelect==null){
            permisosSelect = new ArrayList<Permisos>();
            for (Permisos r : rol.getPermisosCollection())
            permisosSelect.add(r);
        }
        return permisosSelect;
    }

    public void setPermisosSelect(List<Permisos> permisosSelect) {
        this.permisosSelect = permisosSelect;
    }
    
}
