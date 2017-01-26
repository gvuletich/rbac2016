/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Usuariotipo;
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
public class UsuariotipoFacade extends AbstractFacade<Usuariotipo> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariotipoFacade() {
        super(Usuariotipo.class);
    }
    
    public Usuariotipo findById(Integer id){
        Usuariotipo result=null;
        TypedQuery<Usuariotipo> qry = em.createNamedQuery("Usuariotipo.findByIdUsuariotipo", Usuariotipo.class);
        if(id!=null)
            qry.setParameter("idTipo",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public Usuariotipo findByNombre(String nombre){
        Usuariotipo result=null;
        TypedQuery<Usuariotipo> qry = em.createNamedQuery("Usuariotipo.findByNombre", Usuariotipo.class);
        if(nombre!=null)
            qry.setParameter("nombre",nombre);
        result = qry.getSingleResult();
        return result;
    }
    
    public List <Usuariotipo> getAll() {
        Query query = em.createNamedQuery("Usuariotipo.findAll");  
        return query.getResultList();
    }
    
}
