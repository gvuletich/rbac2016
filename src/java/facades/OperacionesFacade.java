/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Operaciones;
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
public class OperacionesFacade extends AbstractFacade<Operaciones> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperacionesFacade() {
        super(Operaciones.class);
    }
    
    public Operaciones findById(Integer id){
        Operaciones result=null;
        TypedQuery<Operaciones> qry = em.createNamedQuery("Operaciones.findByIdOperacion", Operaciones.class);
        if(id!=null)
            qry.setParameter("idOperacion",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public Operaciones findByNombre(String nombre){
        Operaciones result=null;
        TypedQuery<Operaciones> qry = em.createNamedQuery("Operaciones.findByNombre", Operaciones.class);
        if(nombre!=null)
            qry.setParameter("nombre",nombre);
        result = qry.getSingleResult();
        return result;
    }
    
    public List<Operaciones> findByHabilitado(Boolean habilitado){
        List<Operaciones>  result=null;
        Query qry = em.createNamedQuery("Operaciones.findByHabilitado", Operaciones.class);
        if(habilitado!=null)
            qry.setParameter("habilitado",habilitado);
        result=qry.getResultList();
        return result;
    }
    
}
