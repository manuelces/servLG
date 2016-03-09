package com.lg.client.requestfactory;

import java.util.List;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.ElectroProxy;
import com.lg.server.model.process.MantElectro;

@Service(value = MantElectro.class)
public interface CntxMantElectro extends RequestContext {
	Request<Boolean> insertarElectro(ElectroProxy bean);

	Request<Boolean> actualizarElectro(ElectroProxy bean);

	Request<Boolean> eliminarElectro(ElectroProxy bean);

	Request<List<ElectroProxy>> listarElectro();
}
