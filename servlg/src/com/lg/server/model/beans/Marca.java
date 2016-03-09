package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Marca implements Serializable {

	private static final long serialVersionUID = -6263205711579293286L;
	@PrimaryKey
	@Persistent
	private String idMarca;
	@Persistent
	private String descripcion;
	@Persistent
	private String abreviatura;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@Persistent
	private Integer estadoActual = 1;
	@NotPersistent
	private String codeMarca;

	public String getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(String idMarca) {		
		this.idMarca = java.util.UUID.randomUUID().toString();
	}	

	public String getCodeMarca() {
		return codeMarca;
	}

	public void setCodeMarca(String codeMarca) {		
		this.codeMarca = codeMarca;
		this.idMarca=codeMarca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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

	public Integer getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Integer estadoActual) {
		this.estadoActual = estadoActual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMarca == null) ? 0 : idMarca.hashCode());
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
		Marca other = (Marca) obj;
		if (idMarca == null) {
			if (other.idMarca != null)
				return false;
		} else if (!idMarca.equals(other.idMarca))
			return false;
		return true;
	}

}
