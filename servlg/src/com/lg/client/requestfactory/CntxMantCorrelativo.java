package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.CorrelativoProxy;
import com.lg.server.model.process.MantCorrelativo;

@Service(value = MantCorrelativo.class)
public interface CntxMantCorrelativo extends RequestContext {

	Request<Boolean> insertarCorrelativo(CorrelativoProxy bean);

	Request<Boolean> actualizarCorrelativo(CorrelativoProxy bean);

	Request<Boolean> eliminarCorrelativo(CorrelativoProxy bean);

	Request<List<CorrelativoProxy>> listarCorrelativo();

	Request<List<CorrelativoProxy>> listarCorrelativo(String codeTipoDoc);

}
