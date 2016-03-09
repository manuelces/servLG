package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.TipoDocIdenProxy;
import com.lg.server.model.process.MantTipoDocIden;

@Service(value = MantTipoDocIden.class)
public interface CntxMantTipoDocIden extends RequestContext{
	
	Request<Boolean> insertarTipoDocIden(TipoDocIdenProxy bean);
	Request<Boolean> actualizarTipoDocIden(TipoDocIdenProxy bean);
	Request<Boolean> eliminarTipoDocIden(TipoDocIdenProxy bean);
	Request<List<TipoDocIdenProxy>> listarTipoDocIden();
}
