package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -8541051724771161148L;

	@PrimaryKey
	@Persistent
	private String idUsuario;
	@Persistent
	@Unique
	private String login;
	@Persistent
	private String clave;
	@Persistent
	private Long version;
	@Persistent
	@Unowned
	private Trabajador beanTrabajador;
	@Persistent
	private String codeTrabajador;
	@NotPersistent
	private String codeUsuario;
	@Persistent
	private Integer estadoActual = 1;
	@Persistent
	private String codeUsuarioActivaActual;
	@NotPersistent
	private String operacion;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = java.util.UUID.randomUUID().toString();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public String getCodeUsuario() {
		return codeUsuario;
	}

	public void setCodeUsuario(String codeUsuario) {
		this.codeUsuario = codeUsuario;
		this.idUsuario = codeUsuario;
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

	public String getCodeUsuarioActivaActual() {
		return codeUsuarioActivaActual;
	}

	public void setCodeUsuarioActivaActual(String codeUsuarioActivaActual) {
		this.codeUsuarioActivaActual = codeUsuarioActivaActual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

}
