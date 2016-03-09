package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5132490471580470220L;
	
	@PrimaryKey
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idCliente;
	@Persistent
    @Unowned
    private TipoDocIden beanTipoDocIden;
	@Persistent
	private String numDocumento;
	@Persistent
	private String descripcion;
	@Persistent
	@Unowned
	private Distrito beanDistrito;
	@Persistent
	private String direccion;
	@Persistent
	private String tipoPersona;
	@Persistent
	private String celular;
	@Persistent
	private String telefono;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codeProvincia;
	@NotPersistent
	private String descProvincia;
	@Persistent
	private String codeDepartamento;
	@NotPersistent
	private String descDepartamento;
	@Persistent
	private String codePais;
	@NotPersistent
	private String descPais;
	@NotPersistent
	private String codeCliente;
	
	
	
	public String getCodeCliente() {
		return codeCliente;
	}
	public void setCodeCliente(String codeCliente) {
		this.idCliente=codeCliente;
		this.codeCliente = codeCliente;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		Key keyCliente=KeyFactory.createKey(Cliente.class.getSimpleName(), java.util.UUID.randomUUID().toString());
		this.idCliente = KeyFactory.keyToString(keyCliente);		
	}
	public TipoDocIden getBeanTipoDocIden() {
		return beanTipoDocIden;
	}
	public void setBeanTipoDocIden(TipoDocIden beanTipoDocIden) {
		this.beanTipoDocIden = beanTipoDocIden;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Distrito getBeanDistrito() {
		return beanDistrito;
	}
	public void setBeanDistrito(Distrito beanDistrito) {
		this.beanDistrito = beanDistrito;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public String getCodeProvincia() {
		return codeProvincia;
	}
	public void setCodeProvincia(String codeProvincia) {
		this.codeProvincia = codeProvincia;
	}
	public String getDescProvincia() {
		return descProvincia;
	}
	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}
	public String getCodeDepartamento() {
		return codeDepartamento;
	}
	public void setCodeDepartamento(String codeDepartamento) {
		this.codeDepartamento = codeDepartamento;
	}
	public String getDescDepartamento() {
		return descDepartamento;
	}
	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}
	public String getCodePais() {
		return codePais;
	}
	public void setCodePais(String codePais) {
		this.codePais = codePais;
	}
	public String getDescPais() {
		return descPais;
	}
	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}
	
	
}
