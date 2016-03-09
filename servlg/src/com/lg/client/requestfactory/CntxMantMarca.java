package com.lg.client.requestfactory;

import java.util.List;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.MarcaProxy;
import com.lg.server.model.process.MantMarca;

@Service(value = MantMarca.class)
public interface CntxMantMarca extends RequestContext {
	Request<Boolean> insertarMarca(MarcaProxy bean);

	Request<Boolean> actualizarMarca(MarcaProxy bean);

	Request<Boolean> eliminarMarca(MarcaProxy bean);

	Request<List<MarcaProxy>> listarMarca();
}
