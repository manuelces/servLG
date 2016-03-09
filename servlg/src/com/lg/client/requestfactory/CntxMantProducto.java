package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.ProductoProxy;
import com.lg.server.model.process.MantProducto;

@Service(value = MantProducto.class)
public interface CntxMantProducto extends RequestContext {
	Request<Boolean> insertarProducto(ProductoProxy bean);
	Request<Boolean> actualizarProducto(ProductoProxy bean);
	Request<Boolean> eliminarProducto(ProductoProxy bean);
	Request<List<ProductoProxy>> listarProducto();	
}
