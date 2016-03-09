package com.lg.server.model.beans;

import java.io.Serializable;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

@PersistenceCapable(detachable = "true")
public class TipoServicio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7986063077728690152L;

	@PrimaryKey
	@Persistent
	private Key idTipoServicio;
	@Persistent
	@Unique
	private String descripcion;
	@Persistent
	private String abreviatura;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private EmpresaFabricante beanEmpresaFabricante;
	@Persistent
	private String codeEmpresaFabricante;
	@Persistent
	private String codeTipoServicio;
	@Persistent
	private Integer estadoActual = 1;

	public String getIdTipoServicio() {
		return KeyFactory.keyToString(idTipoServicio);
	}

	public void setIdTipoServicio(String idEmpresaFabricante) {
		Key keyEmpresaFabricante = KeyFactory.stringToKey(idEmpresaFabricante);
		this.codeEmpresaFabricante = idEmpresaFabricante;
		Key keyTipoServicio = KeyFactory.createKey(keyEmpresaFabricante,
				TipoServicio.class.getSimpleName(), java.util.UUID.randomUUID().toString());
		this.codeTipoServicio = KeyFactory.keyToString(keyTipoServicio);
		this.idTipoServicio = keyTipoServicio;
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

	public EmpresaFabricante getBeanEmpresaFabricante() {
		return beanEmpresaFabricante;
	}

	public void setBeanEmpresaFabricante(EmpresaFabricante beanEmpresaFabricante) {
		this.beanEmpresaFabricante = beanEmpresaFabricante;
	}

	public String getCodeEmpresaFabricante() {
		return codeEmpresaFabricante;
	}

	public void setCodeEmpresaFabricante(String codeEmpresaFabricante) {
		this.codeEmpresaFabricante = codeEmpresaFabricante;
	}

	public String getCodeTipoServicio() {
		return codeTipoServicio;
	}

	public void setCodeTipoServicio(String codeTipoServicio) {
		this.idTipoServicio = KeyFactory.stringToKey(codeTipoServicio);
		this.codeTipoServicio = codeTipoServicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoServicio == null) ? 0 : idTipoServicio.hashCode());
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
		TipoServicio other = (TipoServicio) obj;
		if (idTipoServicio == null) {
			if (other.idTipoServicio != null)
				return false;
		} else if (!idTipoServicio.equals(other.idTipoServicio))
			return false;
		return true;
	}

}
