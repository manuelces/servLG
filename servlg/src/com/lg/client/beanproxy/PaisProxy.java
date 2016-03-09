package com.lg.client.beanproxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocPais;
import com.lg.server.model.beans.Pais;

@ProxyFor(value = Pais.class, locator = LocPais.class)
public interface PaisProxy  extends EntityProxy{
	public String getIdPais();
	public void setIdPais(String idPais);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);	
	public String getCodigo();
	public void setCodigo(String codigo);
	public String getCodePais();
	public void setCodePais(String codePais);
	public List<DepartamentoProxy> getListDepartamento();
	public void setListDepartamento(List<DepartamentoProxy> listDepartamento);
}
