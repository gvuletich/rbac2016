/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entidades.Cajas;
import facades.CajasFacade;
import java.io.IOException;
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
@Named(value = "cajasView")
@ViewScoped
public class CajasView implements Serializable {
    
    @EJB
    private CajasFacade cajasFacade;   
    private Cajas caja;
    
    private List<Cajas> cajas=null;
    
    public void onRowSelect(SelectEvent event) {
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_cajas.xhtml?faces-redirect=true");
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }
    
    public void nuevoCaja(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("alta_cajas.xhtml?faces-redirect=true");                    
        } catch (IOException ex)
        {}
        return;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public List<Cajas> getCajas() {
        if(cajas==null)
            cajas = cajasFacade.findByHabilitado(true);
        return cajas;
    }

    public void setCajas(List<Cajas> cajas) {
        this.cajas = cajas;
    }
    
    
    
    
}
