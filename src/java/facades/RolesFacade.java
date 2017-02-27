/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Roles;
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
public class RolesFacade extends AbstractFacade<Roles> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }
    
    public Roles findById(Integer id){
        Roles result=null;
        TypedQuery<Roles> qry = em.createNamedQuery("Roles.findByIdRol", Roles.class);
        if(id!=null)
            qry.setParameter("idRol",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public Roles findByNombre(String nombre){
        Roles result=null;
        TypedQuery<Roles> qry = em.createNamedQuery("Roles.findByNombre", Roles.class);
        if(nombre!=null)
            qry.setParameter("nombre",nombre);
        result = qry.getSingleResult();
        return result;
    }
    
    public List<Roles> findByHabilitado(Boolean habilitado){
        List<Roles>  result=null;
        Query qry = em.createNamedQuery("Roles.findByHabilitado", Roles.class);
        if(habilitado!=null)
            qry.setParameter("habilitado",habilitado);
        result=qry.getResultList();
        return result;
    }
    
}
