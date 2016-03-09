package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.CorrelativoActivaProxy;
import com.lg.server.model.process.MantCorrelativoActiva;

@Service(value = MantCorrelativoActiva.class)
public interface CntxMantCorrelativoActiva extends RequestContext {
	Request<Boolean> insertarCorrelativoActiva(CorrelativoActivaProxy bean);

	Request<Boolean> actualizarCorrelativoActiva(CorrelativoActivaProxy bean);

	Request<List<CorrelativoActivaProxy>> listarCorrelativoActiva();
	
	Request<List<CorrelativoActivaProxy>> listarCorrelativoActiva(String codeFamilia);
}

