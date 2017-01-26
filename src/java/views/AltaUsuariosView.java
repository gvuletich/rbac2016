/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Usuarios;
import entidades.Usuariotipo;
import facades.UsuariosFacade;
import facades.UsuariotipoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author german
 */
@Named(value = "altaUsuariosView")
@ViewScoped
public class AltaUsuariosView implements Serializable {
    
    @EJB
    private UsuariosFacade usuariosFacade;   
    private Usuarios usuario;
    
    @EJB
    private UsuariotipoFacade usuariotipoFacade;   
    private List<Usuariotipo> usuariotipos;
    
    private Usuariotipo tipo;
    private String nombre;
    private String password;
    
    /**
     * Creates a new instance of AltaUsuariosView
     */
//    public AltaUsuariosView() {
//        
//    }
    public void crear(){
        usuario = new Usuarios();
        usuario.setIdTipo(tipo);
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        usuario.setHabilitado(true);
        usuariosFacade.edit(usuario);
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
    
}
