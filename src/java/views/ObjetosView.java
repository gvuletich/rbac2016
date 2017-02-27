/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Objetos;
import facades.ObjetosFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author germanU
 */
@Named(value = "objetosView")
@ViewScoped
public class ObjetosView implements Serializable {
    
    @EJB
    private ObjetosFacade objetosFacade;   
    private Objetos objeto;
    
    private List<Objetos> objetos=null;
    
    public void onRowSelect(SelectEvent event) {
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_objetos.xhtml?faces-redirect=true");
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }

    public Objetos getObjeto() {
        return objeto;
    }

    public void setObjeto(Objetos objeto) {
        this.objeto = objeto;
    }

    public List<Objetos> getObjetos() {
        if(objetos==null)
            objetos = objetosFacade.findByHabilitado(true);
        return objetos;
    }

    public void setObjetos(List<Objetos> objetos) {
        this.objetos = objetos;
    }
    
    
    
    
}
