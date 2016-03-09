package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocMarca;
import com.lg.server.model.beans.Marca;

@ProxyFor(value = Marca.class, locator = LocMarca.class)
public interface MarcaProxy extends EntityProxy {

	public String getIdMarca();
	public void setIdMarca(String idMarca);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public String getAbreviatura();
	public void setAbreviatura(String abreviatura);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public Integer getEstadoActual();
	public void setEstadoActual(Integer estadoActual);
	public String getCodeMarca();
	public void setCodeMarca(String codeMarca);
}
