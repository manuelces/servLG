package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.DistritoProxy;
import com.lg.server.model.process.MantDistrito;

@Service(value = MantDistrito.class)
public interface CntxDistrito extends RequestContext {
	Request<Boolean> insertarDistrito(DistritoProxy bean);

	Request<Boolean> actualizarDistrito(DistritoProxy bean);

	Request<Boolean> eliminarDistrito(DistritoProxy bean);

	Request<List<DistritoProxy>> listarDistrito();
	
	Request<List<DistritoProxy>> listarDistrito(String codeFamilia);
}

