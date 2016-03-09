package com.lg.server.model.beans;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class CorrelativoActiva implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7084513483518592434L;

	@PrimaryKey
	@Persistent
	private String idCorrelativoActiva;
	@Persistent
	private Date fechaIni;
	@Persistent
	private Date fechaFin;
	@Persistent
	private Integer estado;
	@Persistent
	@Unowned
	private Correlativo beanCorrelativo;
	@Persistent
	private String codeCorrelativo;
	@NotPersistent
	private String codeCorrelativoActiva;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;

	public String getIdCorrelativoActiva() {
		return idCorrelativoActiva;
	}

	public void setIdCorrelativoActiva(String idCorrelativoActiva) {
		this.idCorrelativoActiva = java.util.UUID.randomUUID().toString();
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

	public Correlativo getBeanCorrelativo() {
		return beanCorrelativo;
	}

	public void setBeanCorrelativo(Correlativo beanCorrelativo) {
		this.beanCorrelativo = beanCorrelativo;
	}

	public String getCodeCorrelativo() {
		return codeCorrelativo;
	}

	public void setCodeCorrelativo(String codeCorrelativo) {
		this.codeCorrelativo = codeCorrelativo;
	}

	public String getCodeCorrelativoActiva() {
		return codeCorrelativoActiva;
	}

	public void setCodeCorrelativoActiva(String codeCorrelativoActiva) {
		this.idCorrelativoActiva = codeCorrelativoActiva;
		this.codeCorrelativoActiva = codeCorrelativoActiva;
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
				+ ((idCorrelativoActiva == null) ? 0 : idCorrelativoActiva
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
		CorrelativoActiva other = (CorrelativoActiva) obj;
		if (idCorrelativoActiva == null) {
			if (other.idCorrelativoActiva != null)
				return false;
		} else if (!idCorrelativoActiva.equals(other.idCorrelativoActiva))
			return false;
		return true;
	}

}
