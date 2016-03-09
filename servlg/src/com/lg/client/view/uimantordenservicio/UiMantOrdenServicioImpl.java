package com.lg.client.view.uimantordenservicio;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ElectroProxy;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.client.beanproxy.TipoServicioProxy;
import com.lg.client.requestfactory.CntxMantElectro;
import com.lg.client.requestfactory.CntxMantEmpresaFabricante;
import com.lg.client.requestfactory.CntxMantTipoDocumento;
import com.lg.client.requestfactory.CntxMantTipoServicio;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.view.uiordenservicio.UiHomeOrdenServicio;

public class UiMantOrdenServicioImpl extends UiMantOrdenServicio {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();

	// private UiHomeOrdenServicio uiHomeOrdenServicio;

	public UiMantOrdenServicioImpl(UiHomeOrdenServicio uiHomeOrdenServicio) {
		// this.uiHomeOrdenServicio = uiHomeOrdenServicio;
		loadEmpresaFabricante();
		loadElectro();
	}

	@Override
	public void loadEmpresaFabricante() {
		// TODO Auto-generated method stub
		CntxMantEmpresaFabricante context = FACTORY.cntxMantEmpresaFabricante();
		FACTORY.initialize(EVENTBUS);
		Request<List<EmpresaFabricanteProxy>> request = context
				.listarEmpresaFabricante();
		request.fire(new Receiver<List<EmpresaFabricanteProxy>>() {

			@Override
			public void onSuccess(List<EmpresaFabricanteProxy> response) {
				lstEmpresaFabricante.setData(response);
				/*
				 * if(bean!=null){ lstEmpresaFabricante.setSelectedItem(bean.
				 * getBeanEmpresaFabricante().getDescripcion()); }
				 */
			}
		});
	}

	@Override
	public void loadTipoServicio() {
		// TODO Auto-generated method stub
		CntxMantTipoServicio context = FACTORY.cntxMantTipoServicio();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoServicioProxy>> request = context
				.listarTipoServicio(lstEmpresaFabricante.getSelectedItem()
						.getIdEmpresaFabricante());
		request.fire(new Receiver<List<TipoServicioProxy>>() {

			@Override
			public void onSuccess(List<TipoServicioProxy> response) {
				lstTipoServicio.setData(response);
				/*
				 * if (bean != null) {
				 * lstSubFamilia.setSelectedItem(bean.getBeanSubFamilia()
				 * .getDescripcion()); }
				 */
			}
		});
	}

	@Override
	public void loadElectro() {
		// TODO Auto-generated method stub
		CntxMantElectro context = FACTORY.cntxMantElectro();
		FACTORY.initialize(EVENTBUS);
		Request<List<ElectroProxy>> request = context.listarElectro();
		request.fire(new Receiver<List<ElectroProxy>>() {

			@Override
			public void onSuccess(List<ElectroProxy> response) {
				lstElectro.setData(response);
				/*
				 * if(bean!=null){
				 * lstElectro.setSelectedItem(bean.getBeanElectro
				 * ().getDescripcion()); }
				 */
			}
		});
	}
	
	@Override
	public void loadTipoDocumento() {
		// TODO Auto-generated method stub
		CntxMantTipoDocumento context = FACTORY.cntxMantTipoDocumento();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoDocumentoProxy>> request = context
				.listarTipoDocumento();
		request.fire(new Receiver<List<TipoDocumentoProxy>>() {
			@Override
			public void onSuccess(List<TipoDocumentoProxy> response) {
				lstTipoDocumento.setData(response);
				/*if (bean != null) {
					lstTipoDocumento.setSelectedItem(bean
							.getBeanTipoDocumento().getDescripcion());
				}*/
			}
		});
	}
	
	@Override
	public void goUISearchAddCliente(){
		
	}
}
