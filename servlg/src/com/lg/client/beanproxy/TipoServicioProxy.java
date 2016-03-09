package com.lg.client.beanproxy;

import com.lg.server.locator.LocTipoServicio;
import com.lg.server.model.beans.TipoServicio;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = TipoServicio.class, locator = LocTipoServicio.class)
public interface TipoServicioProxy extends EntityProxy {
	public String getIdTipoServicio();

	public void setIdTipoServicio(String code);

	public String getDescripcion();

	public void setDescripcion(String descripcion);

	public String getAbreviatura();

	public void setAbreviatura(String abreviatura);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public EmpresaFabricanteProxy getBeanEmpresaFabricante();

	public void setBeanEmpresaFabricante(
			EmpresaFabricanteProxy beanEmpresaFabricante);

	public String getCodeEmpresaFabricante();

	public void setCodeEmpresaFabricante(String codeEmpresaFabricante);

	public String getCodeTipoServicio();

	public void setCodeTipoServicio(String codeTipoServicio);
}
