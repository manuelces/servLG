package com.lg.client.beanproxy;

import com.lg.server.locator.LocTipoDocIden;
import com.lg.server.model.beans.TipoDocIden;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = TipoDocIden.class, locator = LocTipoDocIden.class)
public interface TipoDocIdenProxy extends EntityProxy {

	public String getIdTipoDoc();

	public void setIdTipoDoc(String idTipoDoc);

	public String getDescripcion();

	public void setDescripcion(String descripcion);

	public String getAbrev();

	public void setAbrev(String abrev);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);
	
}
