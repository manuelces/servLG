package com.lg.server.model.beans;

import java.io.Serializable;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Correlativo implements Serializable {

	private static final long serialVersionUID = -8541051724771161148L;

	@PrimaryKey
	@Persistent
	private String idCorrelativo;
	@Persistent
	private String serie;
	@Persistent
	private String preimpreso;
	@Persistent
	private String numInicial;
	@Persistent
	private String numFinal;
	@Persistent
	@Unowned
	private TipoDocumento beanTipoDocumento;
	@Persistent
	private String codeTipoDoc;
	@Persistent
	private Integer estadoActual = 1;
	@NotPersistent
	private String codeCorrelativo;
	@NotPersistent
	private String operacion;
	@Persistent
	private String codeCorrelativoActivaActual;
	@Persistent
	private Long version;

	public String getIdCorrelativo() {
		return idCorrelativo;
	}

	public void setIdCorrelativo(String idCorrelativo) {
		this.idCorrelativo = java.util.UUID.randomUUID().toString();
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getPreimpreso() {
		return preimpreso;
	}

	public void setPreimpreso(String preimpreso) {
		this.preimpreso = preimpreso;
	}

	public TipoDocumento getBeanTipoDocumento() {
		return beanTipoDocumento;
	}

	public void setBeanTipoDocumento(TipoDocumento beanTipoDocumento) {
		this.beanTipoDocumento = beanTipoDocumento;
	}

	public Integer getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Integer estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getCodeCorrelativo() {
		return codeCorrelativo;
	}

	public void setCodeCorrelativo(String codeCorrelativo) {
		this.codeCorrelativo = codeCorrelativo;
		this.idCorrelativo = codeCorrelativo;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getNumInicial() {
		return numInicial;
	}

	public void setNumInicial(String numInicial) {
		this.numInicial = numInicial;
	}

	public String getNumFinal() {
		return numFinal;
	}

	public void setNumFinal(String numFinal) {
		this.numFinal = numFinal;
	}

	public String getCodeTipoDoc() {
		return codeTipoDoc;
	}

	public void setCodeTipoDoc(String codeTipoDoc) {
		this.codeTipoDoc = codeTipoDoc;
	}

	public String getCodeCorrelativoActivaActual() {
		return codeCorrelativoActivaActual;
	}

	public void setCodeCorrelativoActivaActual(
			String codeCorrelativoActivaActual) {
		this.codeCorrelativoActivaActual = codeCorrelativoActivaActual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCorrelativo == null) ? 0 : idCorrelativo.hashCode());
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
		Correlativo other = (Correlativo) obj;
		if (idCorrelativo == null) {
			if (other.idCorrelativo != null)
				return false;
		} else if (!idCorrelativo.equals(other.idCorrelativo))
			return false;
		return true;
	}

}
