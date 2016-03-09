package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocUsuario;
import com.lg.server.model.beans.Usuario;

@ProxyFor(value = Usuario.class, locator = LocUsuario.class)
public interface UsuarioProxy extends EntityProxy {

	public String getIdUsuario();

	public void setIdUsuario(String idUsuario);

	public String getLogin();

	public void setLogin(String login);

	public String getClave();

	public void setClave(String clave);

	public Long getVersion();

	public void setVersion(Long version);

	public TrabajadorProxy getBeanTrabajador();

	public void setBeanTrabajador(TrabajadorProxy beanTrabajador);

	public String getCodeTrabajador();

	public void setCodeTrabajador(String codeTrabajador);

	public String getCodeUsuario();

	public void setCodeUsuario(String codeUsuario);

	public Integer getEstadoActual();

	public void setEstadoActual(Integer estadoActual);

	public String getOperacion();

	public void setOperacion(String operacion);

	public String getCodeUsuarioActivaActual();

	public void setCodeUsuarioActivaActual(String codeUsuarioActivaActual);

}
