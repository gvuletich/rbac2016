/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Objetos;
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
public class ObjetosFacade extends AbstractFacade<Objetos> {

    @PersistenceContext(unitName = "roledemoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetosFacade() {
        super(Objetos.class);
    }
    
    public Objetos findById(Integer id){
        Objetos result=null;
        TypedQuery<Objetos> qry = em.createNamedQuery("Objetos.findByIdObjeto", Objetos.class);
        if(id!=null)
            qry.setParameter("idObjeto",id);
        result=qry.getSingleResult();
        return result;
    }
    
    public Objetos findByNombre(String nombre){
        Objetos result=null;
        TypedQuery<Objetos> qry = em.createNamedQuery("Objetos.findByNombre", Objetos.class);
        if(nombre!=null)
            qry.setParameter("nombre",nombre);
        result = qry.getSingleResult();
        return result;
    }
    
    public List<Objetos> findByHabilitado(Boolean habilitado){
        List<Objetos>  result=null;
        Query qry = em.createNamedQuery("Objetos.findByHabilitado", Objetos.class);
        if(habilitado!=null)
            qry.setParameter("habilitado",habilitado);
        result=qry.getResultList();
        return result;
    }
}
