package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class UsuarioCorrelativo implements Serializable {

	private static final long serialVersionUID = -8541051724771161148L;

	@PrimaryKey
	@Persistent
	private String idUsuarioCorrelativo;
	@Persistent
	@Unowned
	private Usuario beanUsuario;
	@Persistent
	@Unowned
	private Correlativo beanCorrelativo;
	@Persistent
	private String codeUsuario;
	@Persistent
	private String codeCorrelativo;
	@Persistent
	private Integer estadoActual = 1;
	@NotPersistent
	private String operacion;
	@Persistent
	private Long version;
	@NotPersistent
	private String codeUsuarioCorrelativo;

	public String getIdUsuarioCorrelativo() {
		return idUsuarioCorrelativo;
	}

	public void setIdUsuarioCorrelativo(String idUsuarioCorrelativo) {
		this.idUsuarioCorrelativo = java.util.UUID.randomUUID().toString();
	}

	public Usuario getBeanUsuario() {
		return beanUsuario;
	}

	public void setBeanUsuario(Usuario beanUsuario) {
		this.beanUsuario = beanUsuario;
	}

	public Correlativo getBeanCorrelativo() {
		return beanCorrelativo;
	}

	public void setBeanCorrelativo(Correlativo beanCorrelativo) {
		this.beanCorrelativo = beanCorrelativo;
	}

	public String getCodeUsuario() {
		return codeUsuario;
	}

	public void setCodeUsuario(String codeUsuario) {
		this.codeUsuario = codeUsuario;
	}

	public String getCodeCorrelativo() {
		return codeCorrelativo;
	}

	public void setCodeCorrelativo(String codeCorrelativo) {
		this.codeCorrelativo = codeCorrelativo;
	}

	public Integer getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Integer estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCodeUsuarioCorrelativo() {
		return codeUsuarioCorrelativo;
	}

	public void setCodeUsuarioCorrelativo(String codeUsuarioCorrelativo) {
		this.codeUsuarioCorrelativo = codeUsuarioCorrelativo;
		this.idUsuarioCorrelativo = codeCorrelativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idUsuarioCorrelativo == null) ? 0 : idUsuarioCorrelativo
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
		UsuarioCorrelativo other = (UsuarioCorrelativo) obj;
		if (idUsuarioCorrelativo == null) {
			if (other.idUsuarioCorrelativo != null)
				return false;
		} else if (!idUsuarioCorrelativo.equals(other.idUsuarioCorrelativo))
			return false;
		return true;
	}
	
	

}
