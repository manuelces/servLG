package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
//import com.lg.server.model.beans.EmpresaFabricante;
import com.lg.server.model.process.MantEmpresaFabricante;

@Service(value = MantEmpresaFabricante.class)
public interface CntxMantEmpresaFabricante extends RequestContext {
	Request<Boolean> insertarEmpresaFabricante(EmpresaFabricanteProxy bean);
	Request<Boolean> actualizarEmpresaFabricante(EmpresaFabricanteProxy bean);
	Request<Boolean> eliminarEmpresaFabricante(EmpresaFabricanteProxy bean);
	Request<List<EmpresaFabricanteProxy>> listarEmpresaFabricante();	
}
