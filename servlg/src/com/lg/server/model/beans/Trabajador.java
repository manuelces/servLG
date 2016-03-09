package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Trabajador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8541051724771161148L;

	@PrimaryKey
	@Persistent
	private String idTrabajador;
	@Persistent
	private String paterno;
	@Persistent
	private String materno;
	@Persistent
	private String nombre;
	@Persistent
	private String dni;
	@Persistent
	private Long version;
	@Persistent
	@Unowned
	private TipoTrabajador beanTipoTrabajador;
	@Persistent
	private String codeTipoTrabajador;
	@NotPersistent
	private String codeTrabajador;
	@Persistent
	private Integer estadoActual = 1;
	@Persistent
	private String codeTrabajadorActivaActual;
	@NotPersistent
	private String operacion;

	public String getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(String idTrabajador) {
		this.idTrabajador = java.util.UUID.randomUUID().toString();
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public TipoTrabajador getBeanTipoTrabajador() {
		return beanTipoTrabajador;
	}

	public void setBeanTipoTrabajador(TipoTrabajador beanTipoTrabajador) {
		this.beanTipoTrabajador = beanTipoTrabajador;
	}

	public String getCodeTipoTrabajador() {
		return codeTipoTrabajador;
	}

	public void setCodeTipoTrabajador(String codeTipoTrabajador) {
		this.codeTipoTrabajador = codeTipoTrabajador;
	}

	public String getCodeTrabajador() {
		return codeTrabajador;
	}

	public void setCodeTrabajador(String codeTrabajador) {
		this.codeTrabajador = codeTrabajador;
		this.idTrabajador = codeTrabajador;
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
	

	public String getCodeTrabajadorActivaActual() {
		return codeTrabajadorActivaActual;
	}

	public void setCodeTrabajadorActivaActual(String codeTrabajadorActivaActual) {
		this.codeTrabajadorActivaActual = codeTrabajadorActivaActual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTrabajador == null) ? 0 : idTrabajador.hashCode());
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
		Trabajador other = (Trabajador) obj;
		if (idTrabajador == null) {
			if (other.idTrabajador != null)
				return false;
		} else if (!idTrabajador.equals(other.idTrabajador))
			return false;
		return true;
	}

}
