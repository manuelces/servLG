package com.lg.client.beanproxy;

import java.util.Set;

import com.lg.server.locator.LocEmpresaFabricante;
import com.lg.server.model.beans.EmpresaFabricante;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = EmpresaFabricante.class, locator = LocEmpresaFabricante.class)
public interface EmpresaFabricanteProxy extends EntityProxy {
	public String getIdEmpresaFabricante();

	public void setIdEmpresaFabricante(String ruc);

	public String getDescripcion();

	public void setDescripcion(String descripcion);

	public String getRuc();

	public void setRuc(String ruc);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public String getCodeEmpresaFabricante();

	public void setCodeEmpresaFabricante(String codeEmpresaFabricante);

	public Set<TipoServicioProxy> getListTipoServicio();

	public void setListTipoServicio(Set<TipoServicioProxy> listTipoServicio);
}
