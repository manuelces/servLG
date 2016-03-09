package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.server.model.process.MantPais;

@Service(value = MantPais.class)
public interface CntxMantPais extends RequestContext {
	Request<Boolean> insertarPais(PaisProxy bean);

	Request<Boolean> actualizarPais(PaisProxy bean);

	Request<Boolean> eliminarPais(PaisProxy bean);

	Request<List<PaisProxy>> listarPais();
}
