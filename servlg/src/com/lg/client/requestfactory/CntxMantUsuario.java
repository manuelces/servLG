package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.server.model.process.MantUsuario;

@Service(value = MantUsuario.class)
public interface CntxMantUsuario extends RequestContext {

	Request<Boolean> insertarUsuario(UsuarioProxy bean);

	Request<Boolean> eliminarUsuario(UsuarioProxy bean);

	Request<List<UsuarioProxy>> listarUsuario();

}
