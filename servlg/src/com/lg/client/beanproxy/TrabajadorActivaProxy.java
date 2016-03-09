package com.lg.client.beanproxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocTrabajadorActiva;
import com.lg.server.model.beans.TrabajadorActiva;

@ProxyFor(value = TrabajadorActiva.class, locator = LocTrabajadorActiva.class)
public interface TrabajadorActivaProxy extends EntityProxy{
	public String getIdTrabajadorActiva();
	public void setIdTrabajadorActiva(String idTrabajador);
	public Date getFechaIni();
	public void setFechaIni(Date fechaIni);
	public Date getFechaFin();
	public void setFechaFin(Date fechaFin);
	public Integer getEstado();
	public void setEstado(Integer estado);
	public TrabajadorProxy getBeanTrabajador();
	public void setBeanTrabajador(TrabajadorProxy beanTrabajador);
	public String getCodeTrabajador();
	public void setCodeTrabajador(String codeTrabajador);
	public String getCodeTrabajadorActiva();
	public void setCodeTrabajadorActiva(String codeTrabajadorActiva);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
}
