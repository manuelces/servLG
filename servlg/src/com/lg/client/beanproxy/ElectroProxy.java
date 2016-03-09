package com.lg.client.beanproxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocElectro;
import com.lg.server.model.beans.Electro;

@ProxyFor(value = Electro.class, locator = LocElectro.class)
public interface ElectroProxy extends EntityProxy {
	public String getDescripcion();

	public void setDescripcion(String descripcion);

	public String getAbreviatura();

	public void setAbreviatura(String abreviatura);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public List<SubElectroProxy> getListSubElectro();

	public void setListSubElectro(List<SubElectroProxy> listSubElectro);

	public String getIdElectro();

	public void setIdElectro(String idElectro);

	public String getCodeElectro();

	public void setCodeElectro(String codeElectro);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);
}
