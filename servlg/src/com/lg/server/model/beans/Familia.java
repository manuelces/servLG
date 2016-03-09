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
public class Familia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6263205711579293286L;

	@PrimaryKey
	@Persistent
	private Key idFamilia;
	@Persistent
	private String descripcion;
	@Persistent
	private String abreviatura;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent(mappedBy = "beanFamilia")
	private List<SubFamilia> listSubFamilia = new ArrayList<SubFamilia>();
	@Persistent
	private String codeFamilia;
	@Persistent
	private Integer estadoActual=1;

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

	public List<SubFamilia> getListSubFamilia() {
		return listSubFamilia;
	}

	public void setListSubFamilia(List<SubFamilia> listSubFamilia) {
		this.listSubFamilia = listSubFamilia;
	}

	public String getIdFamilia() {
		return KeyFactory.keyToString(idFamilia);
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = KeyFactory.createKey(Familia.class.getSimpleName(),
				java.util.UUID.randomUUID().toString());
		this.codeFamilia = KeyFactory.keyToString(this.idFamilia);
	}

	public String getCodeFamilia() {
		return codeFamilia;
	}

	public void setCodeFamilia(String codeFamilia) {
		this.idFamilia = KeyFactory.stringToKey(codeFamilia);
		this.codeFamilia = codeFamilia;
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
				+ ((idFamilia == null) ? 0 : idFamilia.hashCode());
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
		Familia other = (Familia) obj;
		if (idFamilia == null) {
			if (other.idFamilia != null)
				return false;
		} else if (!idFamilia.equals(other.idFamilia))
			return false;
		return true;
	}

}
