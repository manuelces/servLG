package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocTrabajador;
import com.lg.server.model.beans.Trabajador;

@ProxyFor(value = Trabajador.class, locator = LocTrabajador.class)
public interface TrabajadorProxy extends EntityProxy {

	public String getIdTrabajador();

	public void setIdTrabajador(String idTrabajador);

	public String getPaterno();

	public void setPaterno(String paterno);

	public String getMaterno();

	public void setMaterno(String materno);

	public String getNombre();

	public void setNombre(String nombre);

	public String getDni();

	public void setDni(String dni);

	public Long getVersion();

	public void setVersion(Long version);

	public TipoTrabajadorProxy getBeanTipoTrabajador();

	public void setBeanTipoTrabajador(TipoTrabajadorProxy beanTipoTrabajador);

	public String getCodeTipoTrabajador();

	public void setCodeTipoTrabajador(String codeTipoTrabajador);

	public String getCodeTrabajador();

	public void setCodeTrabajador(String codeTrabajador);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);

	public String getOperacion();

	public void setOperacion(String operacion);
	
	public String getCodeTrabajadorActivaActual();

	public void setCodeTrabajadorActivaActual(String codeTrabajadorActivaActual);
}
