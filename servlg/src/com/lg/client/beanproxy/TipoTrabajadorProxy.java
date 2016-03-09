package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocTipoTrabajador;
import com.lg.server.model.beans.TipoTrabajador;

@ProxyFor(value = TipoTrabajador.class, locator = LocTipoTrabajador.class)
public interface TipoTrabajadorProxy extends EntityProxy {

	public String getIdTipoTrabajador();

	public void setIdTipoTrabajador(String idTipoTrabajador);

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

	public String getCodeTipoTrabajador();

	public void setCodeTipoTrabajador(String codeTipoTrabajador);
}
