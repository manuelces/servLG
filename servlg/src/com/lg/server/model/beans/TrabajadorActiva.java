package com.lg.server.model.beans;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable="true")
public class TrabajadorActiva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7084513483518592434L;
	
	@PrimaryKey
    @Persistent 
	private String idTrabajadorActiva;
	@Persistent
	private Date fechaIni;
	@Persistent
	private Date fechaFin;
	@Persistent
	private Integer estado;
	@Persistent
	@Unowned
	private Trabajador beanTrabajador;
	@Persistent
	private String codeTrabajador;
	@NotPersistent
	private String codeTrabajadorActiva;
	@Persistent
	private Long version;	
	@NotPersistent
	private String operacion;
	
	public String getIdTrabajadorActiva() {
		return idTrabajadorActiva;
	}
	public void setIdTrabajadorActiva(String idTrabajadorActiva) {
		this.idTrabajadorActiva = java.util.UUID.randomUUID().toString();
	}
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Trabajador getBeanTrabajador() {
		return beanTrabajador;
	}
	public void setBeanTrabajador(Trabajador beanTrabajador) {
		this.beanTrabajador = beanTrabajador;
	}
	public String getCodeTrabajador() {
		return codeTrabajador;
	}
	public void setCodeTrabajador(String codeTrabajador) {
		this.codeTrabajador = codeTrabajador;
	}
	public String getCodeTrabajadorActiva() {
		return codeTrabajadorActiva;
	}
	public void setCodeTrabajadorActiva(String codeTrabajadorActiva) {
		this.idTrabajadorActiva=codeTrabajadorActiva;
		this.codeTrabajadorActiva = codeTrabajadorActiva;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idTrabajadorActiva == null) ? 0 : idTrabajadorActiva
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrabajadorActiva other = (TrabajadorActiva) obj;
		if (idTrabajadorActiva == null) {
			if (other.idTrabajadorActiva != null)
				return false;
		} else if (!idTrabajadorActiva.equals(other.idTrabajadorActiva))
			return false;
		return true;
	}
	
	
	
}
