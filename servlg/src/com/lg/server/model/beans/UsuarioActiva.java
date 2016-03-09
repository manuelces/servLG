package com.lg.server.model.beans;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class UsuarioActiva implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7084513483518592434L;

	@PrimaryKey
	@Persistent
	private String idUsuarioActiva;
	@Persistent
	private Date fechaIni;
	@Persistent
	private Date fechaFin;
	@Persistent
	private Integer estado;
	@Persistent
	@Unowned
	private Usuario beanUsuario;
	@Persistent
	private String codeUsuario;
	@NotPersistent
	private String codeUsuarioActiva;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;

	public String getIdUsuarioActiva() {
		return idUsuarioActiva;
	}

	public void setIdUsuarioActiva(String idUsuarioActiva) {
		this.idUsuarioActiva = java.util.UUID.randomUUID().toString();
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

	public Usuario getBeanUsuario() {
		return beanUsuario;
	}

	public void setBeanUsuario(Usuario beanUsuario) {
		this.beanUsuario = beanUsuario;
	}

	public String getCodeUsuario() {
		return codeUsuario;
	}

	public void setCodeUsuario(String codeUsuario) {
		this.codeUsuario = codeUsuario;
	}

	public String getCodeUsuarioActiva() {
		return codeUsuarioActiva;
	}

	public void setCodeUsuarioActiva(String codeUsuarioActiva) {
		this.idUsuarioActiva = codeUsuarioActiva;
		this.codeUsuarioActiva = codeUsuarioActiva;
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
		result = prime * result
				+ ((idUsuarioActiva == null) ? 0 : idUsuarioActiva.hashCode());
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
		UsuarioActiva other = (UsuarioActiva) obj;
		if (idUsuarioActiva == null) {
			if (other.idUsuarioActiva != null)
				return false;
		} else if (!idUsuarioActiva.equals(other.idUsuarioActiva))
			return false;
		return true;
	}

}
