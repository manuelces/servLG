package com.lg.server.model.beans;

import java.io.Serializable;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
public class SubElectro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8541051724771161148L;

	@PrimaryKey
	@Persistent
	private Key idSubElectro;
	@Persistent
	private String descripcion;
	@Persistent
	private String abreviatura;
	@Persistent
	private Long version;
	@Persistent
	private Electro beanElectro;
	@Persistent
	private String codeElectro;
	@Persistent
	private String codeSubElectro;
	@Persistent
	private Integer estadoActual = 1;
	@NotPersistent
	private String operacion;

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

	public Electro getBeanElectro() {
		return beanElectro;
	}

	public void setBeanElectro(Electro beanElectro) {
		this.beanElectro = beanElectro;
	}

	public String getIdSubElectro() {
		return KeyFactory.keyToString(idSubElectro);
	}

	public void setIdSubElectro(String idElectro) {
		Key keyElectro = KeyFactory.stringToKey(idElectro);
		this.codeElectro = idElectro;
		this.idSubElectro = KeyFactory.createKey(keyElectro, SubElectro.class
				.getSimpleName(), java.util.UUID.randomUUID().toString());
		this.codeSubElectro = KeyFactory.keyToString(this.idSubElectro);
	}

	public String getCodeElectro() {
		return codeElectro;
	}

	public void setCodeElectro(String codeElectro) {
		this.codeElectro = codeElectro;
	}

	public String getCodeSubElectro() {
		return codeSubElectro;
	}

	public void setCodeSubElectro(String codeSubElectro) {
		this.idSubElectro = KeyFactory.stringToKey(codeSubElectro);
		this.codeSubElectro = codeSubElectro;
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
				+ ((idSubElectro == null) ? 0 : idSubElectro.hashCode());
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
		SubElectro other = (SubElectro) obj;
		if (idSubElectro == null) {
			if (other.idSubElectro != null)
				return false;
		} else if (!idSubElectro.equals(other.idSubElectro))
			return false;
		return true;
	}

}
