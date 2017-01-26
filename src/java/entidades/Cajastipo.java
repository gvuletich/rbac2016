/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author german
 */
@Entity
@Table(name = "cajastipo", catalog = "roledemo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cajastipo.findAll", query = "SELECT c FROM Cajastipo c")
    , @NamedQuery(name = "Cajastipo.findByIdTipo", query = "SELECT c FROM Cajastipo c WHERE c.idTipo = :idTipo")
    , @NamedQuery(name = "Cajastipo.findByNombre", query = "SELECT c FROM Cajastipo c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cajastipo.findByHabilitado", query = "SELECT c FROM Cajastipo c WHERE c.habilitado = :habilitado")})
public class Cajastipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo")
    private Integer idTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private boolean habilitado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private Collection<Cajas> cajasCollection;

    public Cajastipo() {
    }

    public Cajastipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Cajastipo(Integer idTipo, String nombre, boolean habilitado) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.habilitado = habilitado;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @XmlTransient
    public Collection<Cajas> getCajasCollection() {
        return cajasCollection;
    }

    public void setCajasCollection(Collection<Cajas> cajasCollection) {
        this.cajasCollection = cajasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cajastipo)) {
            return false;
        }
        Cajastipo other = (Cajastipo) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cajastipo[ idTipo=" + idTipo + " ]";
    }
    
}
