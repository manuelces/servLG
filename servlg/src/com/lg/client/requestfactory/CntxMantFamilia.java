package com.lg.client.requestfactory;

import java.util.List;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.FamiliaProxy;
import com.lg.server.model.process.MantFamilia;

@Service(value = MantFamilia.class)
public interface CntxMantFamilia extends RequestContext {
	Request<Boolean> insertarFamilia(FamiliaProxy bean);

	Request<Boolean> actualizarFamilia(FamiliaProxy bean);

	Request<Boolean> eliminarFamilia(FamiliaProxy bean);

	Request<List<FamiliaProxy>> listarFamilia();
}
