package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.UsuarioCorrelativoProxy;
import com.lg.server.model.process.MantUsuarioCorrelativo;

@Service(value = MantUsuarioCorrelativo.class)
public interface CntxMantUsuarioCorrelativo extends RequestContext {

	Request<Boolean> insertarUsuarioCorrelativo(UsuarioCorrelativoProxy bean);

	Request<Boolean> actualizarUsuarioCorrelativo(UsuarioCorrelativoProxy bean);

	Request<Boolean> eliminarUsuarioCorrelativo(UsuarioCorrelativoProxy bean);

	Request<List<UsuarioCorrelativoProxy>> listarUsuarioCorrelativo();

}
