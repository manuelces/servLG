package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.server.model.process.MantDepartamento;

@Service(value = MantDepartamento.class)
public interface CntxMantDepartamento  extends RequestContext {
	Request<Boolean> insertarDepartamento(DepartamentoProxy bean);

	Request<Boolean> actualizarDepartamento(DepartamentoProxy bean);

	Request<Boolean> eliminarDepartamento(DepartamentoProxy bean);

	Request<List<DepartamentoProxy>> listarDepartamento();
	
	Request<List<DepartamentoProxy>> listarDepartamento(String codePais);
}

