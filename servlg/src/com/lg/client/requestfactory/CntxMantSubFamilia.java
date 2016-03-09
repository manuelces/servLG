package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.SubFamiliaProxy;
import com.lg.server.model.process.MantSubFamilia;

@Service(value = MantSubFamilia.class)
public interface CntxMantSubFamilia  extends RequestContext {
	Request<Boolean> insertarSubFamilia(SubFamiliaProxy bean);

	Request<Boolean> actualizarSubFamilia(SubFamiliaProxy bean);

	Request<Boolean> eliminarSubFamilia(SubFamiliaProxy bean);

	Request<List<SubFamiliaProxy>> listarSubFamilia();
	
	Request<List<SubFamiliaProxy>> listarSubFamilia(String codeFamilia);
}

