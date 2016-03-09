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

@PersistenceCapable(detachable="true")
public class SubFamilia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8541051724771161148L;
	
	@PrimaryKey
    @Persistent   
	private Key idSubFamilia;
	@Persistent
	private String descripcion;
	@Persistent
	private String abreviatura;
	@Persistent
	private Long version;	
	@Persistent
	private Familia beanFamilia;
	@Persistent
	private String codeFamilia;
	@Persistent
	private String codeSubFamilia;
	@Persistent
	private Integer estadoActual=1;
	@NotPersistent
	private String operacion;
	@Persistent(mappedBy = "beanSubFamilia")
	private List<Producto> listProducto = new ArrayList<Producto>();
	
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
	public Familia getBeanFamilia() {
		return beanFamilia;
	}
	public void setBeanFamilia(Familia beanFamilia) {
		this.beanFamilia = beanFamilia;
	}
	public String getIdSubFamilia() {
		return KeyFactory.keyToString(idSubFamilia);
	}
	public void setIdSubFamilia(String idFamilia) {
		Key keyFamilia = KeyFactory.stringToKey(idFamilia);
		this.codeFamilia=idFamilia;
		this.idSubFamilia=KeyFactory.createKey(keyFamilia,
				SubFamilia.class.getSimpleName(),java.util.UUID.randomUUID().toString());
		this.codeSubFamilia=KeyFactory.keyToString(this.idSubFamilia);
	}
	public String getCodeFamilia() {
		return codeFamilia;
	}
	public void setCodeFamilia(String codeFamilia) {
		this.codeFamilia = codeFamilia;
	}
	public String getCodeSubFamilia() {
		return codeSubFamilia;
	}
	public void setCodeSubFamilia(String codeSubFamilia) {
		this.idSubFamilia = KeyFactory.stringToKey(codeSubFamilia);		
		this.codeSubFamilia = codeSubFamilia;		
	}
	public Integer getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Integer estadoActual) {
		this.estadoActual = estadoActual;
	}
	public List<Producto> getListProducto() {
		return listProducto;
	}
	public void setListProducto(List<Producto> listProducto) {
		this.listProducto = listProducto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idSubFamilia == null) ? 0 : idSubFamilia.hashCode());
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
		SubFamilia other = (SubFamilia) obj;
		if (idSubFamilia == null) {
			if (other.idSubFamilia != null)
				return false;
		} else if (!idSubFamilia.equals(other.idSubFamilia))
			return false;
		return true;
	}
	

}
