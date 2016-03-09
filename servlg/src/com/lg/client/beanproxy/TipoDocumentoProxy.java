package com.lg.client.beanproxy;

import com.lg.server.locator.LocTipoDocumento;
import com.lg.server.model.beans.TipoDocumento;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = TipoDocumento.class, locator = LocTipoDocumento.class)
public interface TipoDocumentoProxy extends EntityProxy {

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
