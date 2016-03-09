package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Ubigeo implements Serializable {

	private static final long serialVersionUID = -6263205711579293286L;
	@PrimaryKey
	@Persistent
	private String idUbigeo;
	@Persistent
	private String descripcion;
	@Persistent
	private String tipo;// R:Region;P:Provincia;D:Distrito
	@Persistent
	private String idUbigeoPadre;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;

	public String getIdUbigeo() {
		return idUbigeo;
	}

	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdUbigeoPadre() {
		return idUbigeoPadre;
	}

	public void setIdUbigeoPadre(String idUbigeoPadre) {
		this.idUbigeoPadre = idUbigeoPadre;
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
				+ ((idUbigeo == null) ? 0 : idUbigeo.hashCode());
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
		Ubigeo other = (Ubigeo) obj;
		if (idUbigeo == null) {
			if (other.idUbigeo != null)
				return false;
		} else if (!idUbigeo.equals(other.idUbigeo))
			return false;
		return true;
	}

}
