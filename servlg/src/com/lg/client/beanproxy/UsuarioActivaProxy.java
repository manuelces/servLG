package com.lg.client.beanproxy;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocUsuarioActiva;
import com.lg.server.model.beans.UsuarioActiva;

@ProxyFor(value = UsuarioActiva.class, locator = LocUsuarioActiva.class)
public interface UsuarioActivaProxy extends EntityProxy {
	public String getIdUsuarioActiva();

	public void setIdUsuarioActiva(String idUsuario);

	public Date getFechaIni();

	public void setFechaIni(Date fechaIni);

	public Date getFechaFin();

	public void setFechaFin(Date fechaFin);

	public Integer getEstado();

	public void setEstado(Integer estado);

	public UsuarioProxy getBeanUsuario();

	public void setBeanUsuario(UsuarioProxy beanUsuario);

	public String getCodeUsuario();

	public void setCodeUsuario(String codeUsuario);

	public String getCodeUsuarioActiva();

	public void setCodeUsuarioActiva(String codeUsuarioActiva);

	public Long getVersion();

	public void setVersion(Long version);

	public String getOperacion();

	public void setOperacion(String operacion);
}
