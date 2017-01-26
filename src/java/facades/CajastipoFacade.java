/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Cajastipo;
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
public class CajastipoFacade extends AbstractFacade<Cajastipo> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajastipoFacade() {
        super(Cajastipo.class);
    }
    
    public Cajastipo findByNombre(String nombre){
        Cajastipo result=null;
        TypedQuery<Cajastipo> qry = em.createNamedQuery("Cajastipo.findByNombre", Cajastipo.class);
        if(nombre!=null)
            qry.setParameter("nombre",nombre);
        result = qry.getSingleResult();
        return result;
    }
    
    public List <Cajastipo> getAll() {
        Query query = em.createNamedQuery("Cajastipo.findAll");  
        return query.getResultList();
    }
    
}
