package com.lg.client.requestfactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface FactoryGestion extends RequestFactory {
	CntxMantTipoServicio cntxMantTipoServicio();

	CntxMantTipoDocumento cntxMantTipoDocumento();

	CntxMantTipoDocIden cntxMantTipoDocIden();

	CntxMantProducto cntxMantProducto();

	CntxMantFamilia cntxMantFamilia();

	CntxMantSubFamilia cntxMantSubFamilia();

	CntxMantElectro cntxMantElectro();

	CntxMantSubElectro cntxMantSubElectro();

	CntxMantEmpresaFabricante cntxMantEmpresaFabricante();

	CntxMantMarca cntxMantMarca();

	CntxMantTipoTrabajador cntxMantTipoTrabajador();

	CntxMantTrabajador cntxMantTrabajador();

	CntxMantUsuario cntxMantUsuario();

	CntxMantTrabajadorActiva cntxMantTrabajadorActiva();

	CntxMantUsuarioActiva cntxMantUsuarioActiva();

	CntxMantCorrelativoActiva cntxMantCorrelativoActiva();

	CntxMantPais cntxMantPais();

	CntxMantDepartamento cntxMantDepartamento();

	CntxMantProvincia cntxMantProvincia();

	CntxMantDistrito cntxMantDistrito();

	CntxMantCorrelativo cntxMantCorrelativo();

	CntxMantUsuarioCorrelativo cntxMantUsuarioCorrelativo();
	
	CntxMantCliente cntxMantCliente();
}
