package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.server.model.process.MantTipoDocumento;

@Service(value = MantTipoDocumento.class)
public interface CntxMantTipoDocumento extends RequestContext{
	
	Request<Boolean> insertarTipoDocumento(TipoDocumentoProxy bean);
	Request<Boolean> actualizarTipoDocumento(TipoDocumentoProxy bean);
	Request<Boolean> eliminarTipoDocumento(TipoDocumentoProxy bean);
	Request<List<TipoDocumentoProxy>> listarTipoDocumento();
}
