package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.server.model.process.MantTrabajador;

@Service(value = MantTrabajador.class)
public interface CntxMantTrabajador extends RequestContext {

	Request<Boolean> insertarTrabajador(TrabajadorProxy bean);

	Request<Boolean> actualizarTrabajador(TrabajadorProxy bean);

	Request<Boolean> eliminarTrabajador(TrabajadorProxy bean);

	Request<List<TrabajadorProxy>> listarTrabajador();

	//Request<List<TrabajadorProxy>> listarTrabajador(String codeTipoTrabajador);
}
