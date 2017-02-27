/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Operaciones;
import facades.OperacionesFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author germanU
 */
@Named(value = "operacionesView")
@ViewScoped
public class OperacionesView implements Serializable {
    
    @EJB
    private OperacionesFacade operacionesFacade;   
    private Operaciones operacion;
    
    private List<Operaciones> operaciones=null;
    
    public void onRowSelect(SelectEvent event) {
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_operaciones.xhtml?faces-redirect=true");
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }

    public Operaciones getOperacion() {
        return operacion;
    }

    public void setOperacion(Operaciones operacion) {
        this.operacion = operacion;
    }

    public List<Operaciones> getOperaciones() {
        if(operaciones==null)
            operaciones = operacionesFacade.findByHabilitado(true);
        return operaciones;
    }

    public void setOperaciones(List<Operaciones> operaciones) {
        this.operaciones = operaciones;
    }
    
    
    
    
}
