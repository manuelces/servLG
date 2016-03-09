package com.lg.server.model.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
public class EmpresaFabricante implements Serializable {

	private static final long serialVersionUID = -7986063077728690152L;

	@PrimaryKey
	@Persistent
	private Key idEmpresaFabricante;
	@Persistent
	private String descripcion;
	@Persistent
	@Unique
	private String ruc;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent(mappedBy = "beanEmpresaFabricante")
	private Set<TipoServicio> listTipoServicio = new HashSet<TipoServicio>();
	@Persistent
	private String codeEmpresaFabricante;
	@Persistent
	private Integer estadoActual = 1;

	public String getIdEmpresaFabricante() {
		return KeyFactory.keyToString(idEmpresaFabricante);
	}

	public void setIdEmpresaFabricante(String ruc) {
		Key key = KeyFactory.createKey(EmpresaFabricante.class.getSimpleName(),
				java.util.UUID.randomUUID().toString());
		this.codeEmpresaFabricante = KeyFactory.keyToString(key);
		this.idEmpresaFabricante = key;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
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

	public String getCodeEmpresaFabricante() {
		return codeEmpresaFabricante;
	}

	public void setCodeEmpresaFabricante(String codeEmpresaFabricante) {
		this.idEmpresaFabricante = KeyFactory
				.stringToKey(codeEmpresaFabricante);
		this.codeEmpresaFabricante = codeEmpresaFabricante;
	}

	public Set<TipoServicio> getListTipoServicio() {
		return listTipoServicio;
	}

	public void setListTipoServicio(Set<TipoServicio> listTipoServicio) {
		this.listTipoServicio = listTipoServicio;
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
		result = prime
				* result
				+ ((idEmpresaFabricante == null) ? 0 : idEmpresaFabricante
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
		EmpresaFabricante other = (EmpresaFabricante) obj;
		if (idEmpresaFabricante == null) {
			if (other.idEmpresaFabricante != null)
				return false;
		} else if (!idEmpresaFabricante.equals(other.idEmpresaFabricante))
			return false;
		return true;
	}

}
