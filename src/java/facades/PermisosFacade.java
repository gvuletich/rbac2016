/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Permisos;
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
public class PermisosFacade extends AbstractFacade<Permisos> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisosFacade() {
        super(Permisos.class);
    }
    
    public Permisos findById(Integer id){
        Permisos result=null;
        TypedQuery<Permisos> qry = em.createNamedQuery("Permisos.findByIdPermiso", Permisos.class);
        if(id!=null)
            qry.setParameter("idPermiso",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public List<Permisos> findByIdOperacion(Integer id){
        List<Permisos> result=null;
        TypedQuery<Permisos> qry = em.createNamedQuery("Permisos.findByIdOperacion", Permisos.class);
        if(id!=null)
            qry.setParameter("idOperacion",id);
        result=qry.getResultList();
        return result;
    }
    
    public List<Permisos> findByIdObjeto(Integer id){
        List<Permisos> result=null;
        TypedQuery<Permisos> qry = em.createNamedQuery("Permisos.findByIdObjeto", Permisos.class);
        if(id!=null)
            qry.setParameter("idObjeto",id);
        result=qry.getResultList();
        return result;
    }
    
    public Permisos findByIdObjetoOperacion(Integer idObjeto, Integer idOperacion){
        Permisos result=null;
        String isql = "SELECT DISTINCT c FROM Permisos c ";
               isql+= "WHERE c.habilitado = 1 ";
        if(idObjeto!=null)
               isql+= "AND c.idObjeto.idObjeto = :idObjeto ";
        if(idOperacion!=null)
               isql+= "AND c.idOperacion.idOperacion = :idOperacion ";
        TypedQuery<Permisos> qry = em.createQuery(isql, Permisos.class);
        if(idObjeto!=null)
            qry.setParameter("idObjeto",idObjeto);
        if(idOperacion!=null)
            qry.setParameter("idOperacion",idOperacion);
        
        result=qry.getSingleResult();
        return result;
    }
    
    public List<Permisos> findByHabilitado(Boolean habilitado){
        List<Permisos>  result=null;
        Query qry = em.createNamedQuery("Permisos.findByHabilitado", Permisos.class);
        if(habilitado!=null)
            qry.setParameter("habilitado",habilitado);
        result=qry.getResultList();
        return result;
    }
    
}
