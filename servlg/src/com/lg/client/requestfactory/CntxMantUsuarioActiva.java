package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.UsuarioActivaProxy;
import com.lg.server.model.process.MantUsuarioActiva;

@Service(value = MantUsuarioActiva.class)
public interface CntxMantUsuarioActiva extends RequestContext {
	Request<Boolean> insertarUsuarioActiva(UsuarioActivaProxy bean);

	Request<Boolean> actualizarUsuarioActiva(UsuarioActivaProxy bean);

	Request<List<UsuarioActivaProxy>> listarUsuarioActiva();
	
	Request<List<UsuarioActivaProxy>> listarUsuarioActiva(String codeFamilia);
}

