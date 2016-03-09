package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocUbigeo;
import com.lg.server.model.beans.Ubigeo;

@ProxyFor(value = Ubigeo.class, locator = LocUbigeo.class)
public interface UbigeoProxy extends EntityProxy {
	public String getIdUbigeo();

	public void setIdUbigeo(String idUbigeo);

	public String getDescripcion();

	public void setDescripcion(String descripcion);

	public String getTipo();

	public void setTipo(String tipo);

	public String getIdUbigeoPadre();

	public void setIdUbigeoPadre(String idUbigeoPadre);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);
}
