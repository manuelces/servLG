package com.lg.client.beanproxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocDepartamento;
import com.lg.server.model.beans.Departamento;

@ProxyFor(value = Departamento.class, locator = LocDepartamento.class)
public interface DepartamentoProxy  extends EntityProxy{
	public String getIdDepartamento();
	public void setIdDepartamento(String idpais);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public String getCodeDepartamento();
	public void setCodeDepartamento(String codeDepartamento);
	public PaisProxy getBeanPais();
	public void setBeanPais(PaisProxy beanPais);
	public String getCodePais();
	public void setCodePais(String codePais);
	public String getCodigo();
	public void setCodigo(String codigo);
	public List<ProvinciaProxy> getListProvincia();
	public void setListProvincia(List<ProvinciaProxy> listProvincia);
}
