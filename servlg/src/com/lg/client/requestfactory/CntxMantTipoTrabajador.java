package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.TipoTrabajadorProxy;
import com.lg.server.model.process.MantTipoTrabajador;

@Service(value = MantTipoTrabajador.class)
public interface CntxMantTipoTrabajador extends RequestContext {

	Request<Boolean> insertarTipoTrabajador(TipoTrabajadorProxy bean);

	Request<Boolean> actualizarTipoTrabajador(TipoTrabajadorProxy bean);

	Request<Boolean> eliminarTipoTrabajador(TipoTrabajadorProxy bean);

	Request<List<TipoTrabajadorProxy>> listarTipoTrabajador();
}
