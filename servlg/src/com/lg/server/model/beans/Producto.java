package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5134779166727162214L;

	@PrimaryKey
	@Persistent
	private Key idProducto;
	@Persistent
	private String codeProducto;
	@Persistent
	private String descripcion;
	@Persistent
	private String modelo;
	@Persistent
	private SubFamilia beanSubFamilia;
	@Persistent
	private String codeSubFamilia;
	@Persistent
	private String codeFamilia;
	@NotPersistent
	private String descFamilia;
	@Persistent
	@Unowned
	private Marca beanMarca;
	@Persistent
	private String codeMarca;
	@Persistent
	private Integer estadoActual=1;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;

	public String getIdProducto() {
		return KeyFactory.keyToString(idProducto);
	}

	public void setIdProducto(String idSubFamilia) {
		Key keySubFamilia=KeyFactory.stringToKey(idSubFamilia);
		this.codeSubFamilia=idSubFamilia;
		this.idProducto=KeyFactory.createKey(keySubFamilia,Producto.class.getSimpleName(),java.util.UUID.randomUUID().toString());
		this.codeProducto=KeyFactory.keyToString(this.idProducto);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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

	public String getCodeProducto() {
		return codeProducto;
	}

	public void setCodeProducto(String codeProducto) {
		this.idProducto=KeyFactory.stringToKey(codeProducto);
		this.codeProducto = codeProducto;
	}

	public SubFamilia getBeanSubFamilia() {
		return beanSubFamilia;
	}

	public void setBeanSubFamilia(SubFamilia beanSubFamilia) {
		this.beanSubFamilia = beanSubFamilia;
	}

	public String getCodeSubFamilia() {
		return codeSubFamilia;
	}

	public void setCodeSubFamilia(String codeSubFamilia) {
		this.codeSubFamilia = codeSubFamilia;
	}
	
	

	public String getCodeFamilia() {
		return codeFamilia;
	}

	public void setCodeFamilia(String codeFamilia) {
		this.codeFamilia = codeFamilia;
	}

	public String getDescFamilia() {
		return descFamilia;
	}

	public void setDescFamilia(String descFamilia) {
		this.descFamilia = descFamilia;
	}

	public Marca getBeanMarca() {
		return beanMarca;
	}

	public void setBeanMarca(Marca beanMarca) {
		this.beanMarca = beanMarca;
	}

	public String getCodeMarca() {
		return codeMarca;
	}

	public void setCodeMarca(String codeMarca) {
		this.codeMarca = codeMarca;
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
				+ ((idProducto == null) ? 0 : idProducto.hashCode());
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
		Producto other = (Producto) obj;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		return true;
	}

}
