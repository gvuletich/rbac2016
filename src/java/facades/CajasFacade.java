/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Cajas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author german
 */
@Stateless
public class CajasFacade extends AbstractFacade<Cajas> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajasFacade() {
        super(Cajas.class);
    }
    
    public Cajas findById(Integer id){
        Cajas result=null;
        TypedQuery<Cajas> qry = em.createNamedQuery("findByIdCaja", Cajas.class);
        if(id!=null)
            qry.setParameter("idCaja",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public Cajas findByUsuario(Integer idUsuario){
        Cajas result=null;
        TypedQuery<Cajas> qry = em.createNamedQuery("findByIdUsuario", Cajas.class);
        if(idUsuario!=null)
            qry.setParameter("idUsuario",idUsuario);
        result=qry.getSingleResult();
        return result;
    }
    
}
