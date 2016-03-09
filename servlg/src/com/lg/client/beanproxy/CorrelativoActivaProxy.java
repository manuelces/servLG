package com.lg.client.beanproxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocCorrelativoActiva;
import com.lg.server.model.beans.CorrelativoActiva;

@ProxyFor(value = CorrelativoActiva.class, locator = LocCorrelativoActiva.class)
public interface CorrelativoActivaProxy extends EntityProxy{
	public String getIdCorrelativoActiva();
	public void setIdCorrelativoActiva(String idCorrelativo);
	public Date getFechaIni();
	public void setFechaIni(Date fechaIni);
	public Date getFechaFin();
	public void setFechaFin(Date fechaFin);
	public Integer getEstado();
	public void setEstado(Integer estado);
	public CorrelativoProxy getBeanCorrelativo();
	public void setBeanCorrelativo(CorrelativoProxy beanCorrelativo);
	public String getCodeCorrelativo();
	public void setCodeCorrelativo(String codeCorrelativo);
	public String getCodeCorrelativoActiva();

	public void setCodeCorrelativoActiva(String codeCorrelativoActiva);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
}
