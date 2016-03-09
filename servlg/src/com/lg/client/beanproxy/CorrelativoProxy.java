package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocCorrelativo;
import com.lg.server.model.beans.Correlativo;

@ProxyFor(value = Correlativo.class, locator = LocCorrelativo.class)
public interface CorrelativoProxy extends EntityProxy {

	public String getIdCorrelativo();

	public void setIdCorrelativo(String idCorrelativo);

	public String getSerie();

	public void setSerie(String serie);

	public String getPreimpreso();

	public void setPreimpreso(String preimpreso);

	public TipoDocumentoProxy getBeanTipoDocumento();

	public void setBeanTipoDocumento(TipoDocumentoProxy beanTipoDocumento);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);

	public String getCodeCorrelativo();

	public void setCodeCorrelativo(String codeCorrelativo);

	public String getOperacion();

	public void setOperacion(String operacion);

	public Long getVersion();

	public void setVersion(Long version);

	public String getNumInicial();

	public void setNumInicial(String numInicial);

	public String getNumFinal();

	public void setNumFinal(String numFinal);

	public String getCodeTipoDoc();

	public void setCodeTipoDoc(String codeTipoDoc);

	public String getCodeCorrelativoActivaActual();

	public void setCodeCorrelativoActivaActual(
			String codeCorrelativoActivaActual);

}
