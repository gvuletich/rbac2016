/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author german
 */
@Entity
@Table(name = "permisos", catalog = "roledemo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos.findAll", query = "SELECT p FROM Permisos p")
    , @NamedQuery(name = "Permisos.findByIdPermiso", query = "SELECT p FROM Permisos p WHERE p.idPermiso = :idPermiso")
    , @NamedQuery(name = "Permisos.findByHabilitado", query = "SELECT p FROM Permisos p WHERE p.habilitado = :habilitado")})
public class Permisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPermiso")
    private Integer idPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private boolean habilitado;
    @ManyToMany(mappedBy = "permisosCollection", fetch = FetchType.LAZY)
    private Collection<Roles> rolesCollection;
    @JoinColumn(name = "idOperacion", referencedColumnName = "idOperacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Operaciones idOperacion;
    @JoinColumn(name = "idObjeto", referencedColumnName = "idObjeto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Objetos idObjeto;

    public Permisos() {
    }

    public Permisos(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permisos(Integer idPermiso, boolean habilitado) {
        this.idPermiso = idPermiso;
        this.habilitado = habilitado;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @XmlTransient
    public Collection<Roles> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    public Operaciones getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Operaciones idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Objetos getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Objetos idObjeto) {
        this.idObjeto = idObjeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos)) {
            return false;
        }
        Permisos other = (Permisos) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Permisos[ idPermiso=" + idPermiso + " ]";
    }
    
}
