/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Cajas;
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
        TypedQuery<Cajas> qry = em.createNamedQuery("Cajas.findByIdCaja", Cajas.class);
        if(id!=null)
            qry.setParameter("idCaja",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public List<Cajas> findByUsuario(Usuarios idUsuario){
        List<Cajas> result=null;
        TypedQuery<Cajas> qry = em.createNamedQuery("Cajas.findByIdUsuario", Cajas.class);
        if(idUsuario!=null)
            qry.setParameter("idUsuario",idUsuario);
        result=qry.getResultList();
        return result;
    }
    
     public List<Cajas> findByHabilitado(Boolean habilitado){
        List<Cajas>  result=null;
        Query qry = em.createNamedQuery("Cajas.findByHabilitado", Cajas.class);
        if(habilitado!=null)
            qry.setParameter("habilitado",habilitado);
        result=qry.getResultList();
        return result;
    }
    
}
