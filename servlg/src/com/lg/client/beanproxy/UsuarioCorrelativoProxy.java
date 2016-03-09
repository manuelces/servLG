package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocUsuarioCorrelativo;
import com.lg.server.model.beans.UsuarioCorrelativo;

@ProxyFor(value = UsuarioCorrelativo.class, locator = LocUsuarioCorrelativo.class)
public interface UsuarioCorrelativoProxy extends EntityProxy {
	public String getIdUsuarioCorrelativo();

	public void setIdUsuarioCorrelativo(String idUsuarioCorrelativo);

	public UsuarioProxy getBeanUsuario();

	public void setBeanUsuario(UsuarioProxy beanUsuario);

	public CorrelativoProxy getBeanCorrelativo();

	public void setBeanCorrelativo(CorrelativoProxy beanCorrelativo);

	public String getCodeUsuario();

	public void setCodeUsuario(String codeUsuario);

	public String getCodeCorrelativo();

	public void setCodeCorrelativo(String codeCorrelativo);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);

	public String getOperacion();

	public void setOperacion(String operacion);

	public Long getVersion();

	public void setVersion(Long version);

	public String getCodeUsuarioCorrelativo();

	public void setCodeUsuarioCorrelativo(String codeUsuarioCorrelativo);
}
