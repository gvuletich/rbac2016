/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acceso;

import entidades.Objetos;
import entidades.Operaciones;
import entidades.Permisos;
import entidades.Roles;
import entidades.Usuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author german
 */

@ManagedBean(name = "controlAcceso")
@SessionScoped

public class ControlAcceso {
    
    @EJB
    private facades.PermisosFacade permisosFacade;
    @EJB
    private facades.ObjetosFacade objetosFacade;
    @EJB
    private facades.OperacionesFacade operacionesFacade;
    
    public void ControlAcceso(){
        
    }
    
    public Boolean hayPermiso(Objetos o, Operaciones p){
        return permisosFacade.findByIdObjetoOperacion(o.getIdObjeto(), p.getIdOperacion())!=null;
    }
    
    public Boolean tieneRol(Usuarios u, Roles r ){
        return u.getRolesCollection().contains(r);
    }
    
    List <Permisos> permisos(Objetos o){
        return permisosFacade.findByIdObjeto(o.getIdObjeto());
    }
    
    public Permisos getPermiso(Objetos o, Operaciones p){
        return permisosFacade.findByIdObjetoOperacion(o.getIdObjeto(), p.getIdOperacion());
    }
    
    public Boolean hayObjeto(String ostr){
        return objetosFacade.findByNombre(ostr)!=null;
    }
    
    public Boolean hayOperacion(String pstr){
        return operacionesFacade.findByNombre(pstr)!=null;
    }
    
    public Boolean permitidoRol(Roles r, Objetos o, Operaciones p){
        return r.getPermisosCollection().contains(permisosFacade.findByIdObjetoOperacion(o.getIdObjeto(), p.getIdOperacion()));
    }
    
    public Boolean permitidoUsuario(Usuarios u, String o, String p){
        Objetos os = objetosFacade.findByNombre(o);
        Operaciones ps = operacionesFacade.findByNombre(p);
        for (Roles r : u.getRolesCollection())
            if (r.getPermisosCollection().contains(getPermiso(os,ps)))
                return true;
        return false;
    }
    
    
}
