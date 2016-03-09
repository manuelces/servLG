package com.lg.server.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
public class Electro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6263205711579293286L;

	@PrimaryKey
	@Persistent
	private Key idElectro;
	@Persistent
	private String descripcion;
	@Persistent
	private String abreviatura;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent(mappedBy = "beanElectro")
	private List<SubElectro> listSubElectro = new ArrayList<SubElectro>();
	@Persistent
	private String codeElectro;
	@Persistent
	private Integer estadoActual = 1;

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

	public List<SubElectro> getListSubElectro() {
		return listSubElectro;
	}

	public void setListSubElectro(List<SubElectro> listSubElectro) {
		this.listSubElectro = listSubElectro;
	}

	public String getIdElectro() {
		return KeyFactory.keyToString(idElectro);
	}

	public void setIdElectro(String idElectro) {
		this.idElectro = KeyFactory.createKey(Electro.class.getSimpleName(),
				java.util.UUID.randomUUID().toString());
		this.codeElectro = KeyFactory.keyToString(this.idElectro);
	}

	public String getCodeElectro() {
		return codeElectro;
	}

	public void setCodeElectro(String codeElectro) {
		this.idElectro = KeyFactory.stringToKey(codeElectro);
		this.codeElectro = codeElectro;
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
		result = prime * result
				+ ((idElectro == null) ? 0 : idElectro.hashCode());
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
		Electro other = (Electro) obj;
		if (idElectro == null) {
			if (other.idElectro != null)
				return false;
		} else if (!idElectro.equals(other.idElectro))
			return false;
		return true;
	}

}
