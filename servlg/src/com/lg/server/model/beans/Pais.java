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
public class Pais implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4204342672595368670L;
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPais;
	@Persistent
	private String codigo;
	@Persistent
	private String descripcion;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;	
	@NotPersistent
	private String codePais;
	@Persistent(mappedBy = "beanPais")
	private List<Departamento> listDepartamento=new ArrayList<Departamento>();
	
	public String getIdPais() {
		return idPais;
	}
	public void setIdPais(String idPais) {
		Key keyPais=KeyFactory.createKey(Pais.class.getSimpleName(), java.util.UUID.randomUUID().toString());
		this.idPais = KeyFactory.keyToString(keyPais);		
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.idPais=codePais;
		this.codePais = codePais;
	}
	
	public List<Departamento> getListDepartamento() {
		return listDepartamento;
	}
	public void setListDepartamento(List<Departamento> listDepartamento) {
		this.listDepartamento = listDepartamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPais == null) ? 0 : idPais.hashCode());
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
		Pais other = (Pais) obj;
		if (idPais == null) {
			if (other.idPais != null)
				return false;
		} else if (!idPais.equals(other.idPais))
			return false;
		return true;
	}
		
}
