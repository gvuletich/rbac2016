/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Roles;
import entidades.Usuarios;
import entidades.Usuariotipo;
import facades.RolesFacade;
import facades.UsuariosFacade;
import facades.UsuariotipoFacade;
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
@ManagedBean(name = "altaUsuariosView")
@ViewScoped
public class AltaUsuariosView implements Serializable {
    
    @EJB
    private UsuariosFacade usuariosFacade;   
    private Usuarios usuario;
    
    @EJB
    private UsuariotipoFacade usuariotipoFacade;   
    private List<Usuariotipo> usuariotipos;
    
    @EJB
    private RolesFacade rolesFacade;   
    private List<Roles> roles;
    private List<Roles> rolesSelect;    
    private Roles rol;
   
    private Usuariotipo tipo;
    private String nombre;
    private String password;
    
    @Resource
    UserTransaction tx;
    
    /**
     * Creates a new instance of AltaUsuariosView
     */
//    public AltaUsuariosView() {
//        
//    }
    
    private void reset(){    
        usuario=null;
        roles=null;
        rolesSelect=null;
        nombre=null;
        password=null;
    }
    
    @PostConstruct
    public void init(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")!=null){
            usuario = usuariosFacade.findById(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
            if(usuario!=null){
               // getRolesSelect();
                preparaEditar();
            }else
                ;//redirect to somewhere
            }
        else{
            preparaCrear();
        }
    }
    
    public void nuevoUsuario(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_usuario.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }
    
    public void preparaCrear(){
        usuario = new Usuarios();
        //crear();
    }
    
    public void preparaEditar(){
        // falta implementar
    }
    
    public String crear(){

//        usuario.setIdTipo(tipo);
//        usuario.setNombre(nombre);
//        usuario.setPassword(password);
        usuario.setHabilitado(true);
        try{
            //usuario.setRolesCollection(rolesSelect);
            usuariosFacade.edit(usuario);
        
        } catch(Exception e){
            
            System.out.println(e);
        }
        return "/faces/altas/usuarios.xhtml?faces-redirect=true";
        
    }
    
    public String eliminar(){
        usuariosFacade.remove(usuario);
        //usuario.setHabilitado(false);
        //usuariosFacade.edit(usuario);
        reset();
        return "/faces/altas/usuarios.xhtml?faces-redirect=true";
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Usuariotipo getTipo() {
        return tipo;
    }

    public void setTipo(Usuariotipo tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Usuariotipo> getUsuariotipos() {
        if (usuariotipos==null)
            usuariotipos=usuariotipoFacade.getAll();
            
        return usuariotipos;
    }

    public void setUsuariotipos(List<Usuariotipo> usuariotipos) {
        this.usuariotipos = usuariotipos;
    }
    
    public List<Roles> getRoles() {
        if (roles==null)
            roles = rolesFacade.findAll();
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
    public List<Roles> getRolesSelect() {
        if(rolesSelect==null){
            rolesSelect = new ArrayList<Roles>();
            for (Roles r : usuario.getRolesCollection())
            rolesSelect.add(r);
        }
        return rolesSelect;
    }

    public void setRolesSelect(List<Roles> rolesSelect) {
        this.rolesSelect = rolesSelect;
    }
    
}
