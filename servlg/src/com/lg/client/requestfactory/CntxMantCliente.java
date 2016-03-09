package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.ClienteProxy;
import com.lg.server.model.process.MantCliente;

@Service(value = MantCliente.class)
public interface CntxMantCliente extends RequestContext {
	Request<Boolean> insertarCliente(ClienteProxy bean);

	Request<Boolean> actualizarCliente(ClienteProxy bean);

	Request<Boolean> eliminarCliente(ClienteProxy bean);

	Request<List<ClienteProxy>> listarCliente();
}
