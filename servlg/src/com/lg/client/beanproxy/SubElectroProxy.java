package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocSubElectro;
import com.lg.server.model.beans.SubElectro;

@ProxyFor(value = SubElectro.class, locator = LocSubElectro.class)
public interface SubElectroProxy extends EntityProxy {
	public String getDescripcion();

	public void setDescripcion(String descripcion);

	public String getAbreviatura();

	public void setAbreviatura(String abreviatura);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public ElectroProxy getBeanElectro();

	public void setBeanElectro(ElectroProxy beanElectro);

	public String getIdSubElectro();

	public void setIdSubElectro(String code);

	public String getCodeElectro();

	public void setCodeElectro(String codeElectro);

	public String getCodeSubElectro();

	public void setCodeSubElectro(String codeSubElectro);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);
	
}
