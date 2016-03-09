package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.TrabajadorActivaProxy;
import com.lg.server.model.process.MantTrabajadorActiva;

@Service(value = MantTrabajadorActiva.class)
public interface CntxMantTrabajadorActiva extends RequestContext {
	Request<Boolean> insertarTrabajadorActiva(TrabajadorActivaProxy bean);

	Request<Boolean> actualizarTrabajadorActiva(TrabajadorActivaProxy bean);

	Request<List<TrabajadorActivaProxy>> listarTrabajadorActiva();
	
	Request<List<TrabajadorActivaProxy>> listarTrabajadorActiva(String codeFamilia);
}

