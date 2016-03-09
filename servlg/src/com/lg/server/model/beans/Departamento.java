package com.lg.server.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable(detachable = "true")
public class Departamento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2029238048901900816L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idDepartamento;
	@Persistent
	private String codigo;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@Persistent
	private Pais beanPais;
	@Persistent
	private String codePais;
	@NotPersistent
	private String operacion;
	@NotPersistent
	private String codeDepartamento;
	@Persistent(mappedBy = "beanDepartamento")
	private List<Provincia> listProvincia=new ArrayList<Provincia>();	
	
	public String getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(String idpais) {		
		Key keyPais=KeyFactory.stringToKey(idpais);
		Key keyDepartamento=KeyFactory.createKey(keyPais,Departamento.class.getSimpleName(), java.util.UUID.randomUUID().toString());
		this.idDepartamento = KeyFactory.keyToString(keyDepartamento);
		
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getCodeDepartamento() {
		return codeDepartamento;
	}
	public void setCodeDepartamento(String codeDepartamento) {
		this.idDepartamento=codeDepartamento;
		this.codeDepartamento = codeDepartamento;
	}
	public Pais getBeanPais() {
		return beanPais;
	}
	public void setBeanPais(Pais beanPais) {
		this.beanPais = beanPais;
	}
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public List<Provincia> getListProvincia() {
		return listProvincia;
	}
	public void setListProvincia(List<Provincia> listProvincia) {
		this.listProvincia = listProvincia;
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idDepartamento == null) ? 0 : idDepartamento.hashCode());
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
		Departamento other = (Departamento) obj;
		if (idDepartamento == null) {
			if (other.idDepartamento != null)
				return false;
		} else if (!idDepartamento.equals(other.idDepartamento))
			return false;
		return true;
	}
	
	
}
