package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.SubElectroProxy;
import com.lg.server.model.process.MantSubElectro;

@Service(value = MantSubElectro.class)
public interface CntxMantSubElectro extends RequestContext {
	Request<Boolean> insertarSubElectro(SubElectroProxy bean);

	Request<Boolean> actualizarSubElectro(SubElectroProxy bean);

	Request<Boolean> eliminarSubElectro(SubElectroProxy bean);

	Request<List<SubElectroProxy>> listarSubElectro();

	Request<List<SubElectroProxy>> listarSubElectro(String codeElectro);
}
