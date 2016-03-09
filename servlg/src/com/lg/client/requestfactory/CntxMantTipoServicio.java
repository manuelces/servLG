package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.TipoServicioProxy;
//import com.lg.server.model.beans.TipoServicio;
import com.lg.server.model.process.MantTipoServicio;

@Service(value = MantTipoServicio.class)
public interface CntxMantTipoServicio extends RequestContext {
	Request<Boolean> insertarTipoServicio(TipoServicioProxy bean);

	Request<Boolean> actualizarTipoServicio(TipoServicioProxy bean);

	Request<Boolean> eliminarTipoServicio(TipoServicioProxy bean);

	Request<List<TipoServicioProxy>> listarTipoServicio();

	Request<List<TipoServicioProxy>> listarTipoServicio(
			String codeEmpresaFabricante);
}
