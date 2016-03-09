package com.lg.client.beanproxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocProvincia;
import com.lg.server.model.beans.Provincia;

@ProxyFor(value = Provincia.class, locator = LocProvincia.class)
public interface ProvinciaProxy extends EntityProxy{
	public String getIdProvincia();
	public void setIdProvincia(String idDepartamento);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);	
	public String getCodeProvincia();
	public void setCodeProvincia(String codeProvincia);
	public String getCodeDepartamento();
	public void setCodeDepartamento(String codeDepartamento);
	public DepartamentoProxy getBeanDepartamento();
	public void setBeanDepartamento(DepartamentoProxy beanDepartamento);	
	public String getCodigo();
	public void setCodigo(String codigo);	
	public String getCodePais();
	public void setCodePais(String codePais);
	public List<DistritoProxy> getListDistrito();
	public void setListDistrito(List<DistritoProxy> listDistrito);
	public String getDescPais();
	public void setDescPais(String descPais);
}
