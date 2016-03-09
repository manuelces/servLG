package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocDistrito;
import com.lg.server.model.beans.Distrito;

@ProxyFor(value = Distrito.class, locator = LocDistrito.class)
public interface DistritoProxy extends EntityProxy{
	public String getCodeDepartamento();
	public void setCodeDepartamento(String codeDepartamento);
	public String getDescDepartamento();
	public void setDescDepartamento(String descDepartamento);
	public String getCodePais();
	public void setCodePais(String codePais);
	public String getDescPais();
	public void setDescPais(String descPais);
	public String getIdDistrito();
	public void setIdDistrito(String idProvincia);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public String getCodeDistrito();
	public void setCodeDistrito(String codeDistrito);
	public String getCodeProvincia();
	public void setCodeProvincia(String codeProvincia);
	public ProvinciaProxy getBeanProvincia();
	public void setBeanProvincia(ProvinciaProxy beanProvincia);	
	public String getCodigo();
	public void setCodigo(String codigo);
}
