/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author german
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public Usuarios findById(Integer id){
        Usuarios result=null;
        TypedQuery<Usuarios> qry = em.createNamedQuery("Usuarios.findByIdUsuario", Usuarios.class);
        if(id!=null)
            qry.setParameter("idUsuario",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public Usuarios findByNombre(String nombre){
        Usuarios result=null;
        TypedQuery<Usuarios> qry = em.createNamedQuery("Usuarios.findByNombre", Usuarios.class);
        if(nombre!=null)
            qry.setParameter("nombre",nombre);
        result = qry.getSingleResult();
        return result;
    }
    
    public List<Usuarios> findByHabilitado(Boolean habilitado){
        List<Usuarios>  result=null;
        Query qry = em.createNamedQuery("Usuarios.findByHabilitado", Usuarios.class);
        if(habilitado!=null)
            qry.setParameter("habilitado",habilitado);
        result=qry.getResultList();
        return result;
    }
    
}
