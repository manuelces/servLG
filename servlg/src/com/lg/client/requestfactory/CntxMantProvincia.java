package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.server.model.process.MantProvincia;

@Service(value = MantProvincia.class)
public interface CntxMantProvincia extends RequestContext {
	Request<Boolean> insertarProvincia(ProvinciaProxy bean);

	Request<Boolean> actualizarProvincia(ProvinciaProxy bean);

	Request<Boolean> eliminarProvincia(ProvinciaProxy bean);

	Request<List<ProvinciaProxy>> listarProvincia();
	
	Request<List<ProvinciaProxy>> listarProvincia(String codeFamilia);
}

