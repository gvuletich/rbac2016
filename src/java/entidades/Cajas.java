/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author german
 */
@Entity
@Table(name = "cajas", catalog = "roledemo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cajas.findAll", query = "SELECT c FROM Cajas c")
    , @NamedQuery(name = "Cajas.findByIdCaja", query = "SELECT c FROM Cajas c WHERE c.idCaja = :idCaja")
    , @NamedQuery(name = "Cajas.findByIdUsuario", query = "SELECT c FROM Cajas c WHERE c.idUsuario = :idUsuario")
    , @NamedQuery(name = "Cajas.findByMsg", query = "SELECT c FROM Cajas c WHERE c.msg = :msg")
    , @NamedQuery(name = "Cajas.findByHabilitado", query = "SELECT c FROM Cajas c WHERE c.habilitado = :habilitado")})
public class Cajas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaja")
    private Integer idCaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;
    @Size(max = 200)
    @Column(name = "msg")
    private String msg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private boolean habilitado;
    @JoinColumn(name = "idTipo", referencedColumnName = "idTipo")
    @ManyToOne(optional = false)
    private Cajastipo idTipo;

    public Cajas() {
    }

    public Cajas(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public Cajas(Integer idCaja, int idUsuario, boolean habilitado) {
        this.idCaja = idCaja;
        this.idUsuario = idUsuario;
        this.habilitado = habilitado;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Cajastipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Cajastipo idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaja != null ? idCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajas)) {
            return false;
        }
        Cajas other = (Cajas) object;
        if ((this.idCaja == null && other.idCaja != null) || (this.idCaja != null && !this.idCaja.equals(other.idCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cajas[ idCaja=" + idCaja + " ]";
    }
    
}
