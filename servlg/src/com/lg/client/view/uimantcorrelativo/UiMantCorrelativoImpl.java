package com.lg.client.view.uimantcorrelativo;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.CorrelativoActivaProxy;
import com.lg.client.beanproxy.CorrelativoProxy;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.client.requestfactory.CntxMantCorrelativo;
import com.lg.client.requestfactory.CntxMantCorrelativoActiva;
import com.lg.client.requestfactory.CntxMantTipoDocumento;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uicorrelativo.UiHomeCorrelativo;

public class UiMantCorrelativoImpl extends UiMantCorrelativo {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeCorrelativo uiHomeCorrelativo;

	public UiMantCorrelativoImpl(UiHomeCorrelativo uiHomeCorrelativo) {
		this.uiHomeCorrelativo = uiHomeCorrelativo;
		loadTipoDocumento();
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
				if (bean != null) {
					lstTipoDocumento.setSelectedItem(bean
							.getBeanTipoDocumento().getDescripcion());
				}
			}
		});
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantCorrelativo context = FACTORY.cntxMantCorrelativo();
		FACTORY.initialize(EVENTBUS);
		CorrelativoProxy bean = context.create(CorrelativoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setSerie(txtSerie.getText().toUpperCase());
		bean.setPreimpreso(txtPreimpreso.getText().toUpperCase());
		bean.setNumInicial(txtNumInicial.getText().toUpperCase());
		bean.setNumFinal(txtNumFinal.getText().toUpperCase());
		bean.setIdCorrelativo(lstTipoDocumento.getSelectedItem().getIdTipoDoc());
		bean.setBeanTipoDocumento(lstTipoDocumento.getSelectedItem());
		bean.setCodeTipoDoc(lstTipoDocumento.getSelectedItem().getIdTipoDoc());
		Request<Boolean> request = context.insertarCorrelativo(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					cleanForm();
					//Window.alert("Insertado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
	            	not.showPopup();
				} else {
					//Window.alert("Error al insertar");
					Notification not=new Notification(Notification.ALERT,"Error al insertar");
	            	not.showPopup();
				}
			}
		});
	}

	@Override
	public void processActualizar() {
		Date fecha = new Date();
		CntxMantCorrelativo context = FACTORY.cntxMantCorrelativo();
		FACTORY.initialize(EVENTBUS);
		CorrelativoProxy bean = context.create(CorrelativoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setSerie(txtSerie.getText().toUpperCase());
		bean.setPreimpreso(txtPreimpreso.getText().toUpperCase());
		bean.setNumInicial(txtNumInicial.getText().toUpperCase());
		bean.setNumFinal(txtNumFinal.getText().toUpperCase());
		bean.setCodeCorrelativo(this.bean.getIdCorrelativo());
		bean.setBeanTipoDocumento(lstTipoDocumento.getSelectedItem());
		bean.setCodeTipoDoc(lstTipoDocumento.getSelectedItem().getIdTipoDoc());
		Request<Boolean> request = context.actualizarCorrelativo(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
	            	not.showPopup();
					goToUiCorrelativo();
				} else {
					//Window.alert("Error al actualizar");
					Notification not=new Notification(Notification.ALERT,"Error al actualizar");
	            	not.showPopup();
				}
			}
		});
	}

	@Override
	public void processEliminar() {
		Date fecha = new Date();
		CntxMantCorrelativo context = FACTORY.cntxMantCorrelativo();
		FACTORY.initialize(EVENTBUS);
		CorrelativoProxy bean = context.create(CorrelativoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setCodeCorrelativo(this.bean.getIdCorrelativo());
		bean.setSerie(txtSerie.getText().toUpperCase());
		bean.setPreimpreso(txtPreimpreso.getText().toUpperCase());
		bean.setNumInicial(txtNumInicial.getText().toUpperCase());
		bean.setNumFinal(txtNumFinal.getText().toUpperCase());
		Request<Boolean> request = context.eliminarCorrelativo(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
	            	not.showPopup();
					goToUiCorrelativo();
				} else {
					//Window.alert("Error al eliminar");
					Notification not=new Notification(Notification.ALERT,"Error al eliminar");
	            	not.showPopup();
				}
			}
		});
	}

	@Override
	public void processActivar() {
		// TODO Auto-generated method stub

		Date fecha = new Date();
		CntxMantCorrelativoActiva context = FACTORY.cntxMantCorrelativoActiva();
		FACTORY.initialize(EVENTBUS);
		CorrelativoActivaProxy bean = context
				.create(CorrelativoActivaProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setCodeCorrelativoActiva(this.bean.getIdCorrelativo());
		bean.setCodeCorrelativo(this.bean.getIdCorrelativo());
		Request<Boolean> request = context.insertarCorrelativoActiva(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					//Window.alert("Activado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Activado correctamente");
	            	not.showPopup();
					goToUiCorrelativo();
				} else {
					//Window.alert("Error al activar");
					Notification not=new Notification(Notification.ALERT,"Error al activar");
	            	not.showPopup();
				}
			}

		});

	}

	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub

		Date fecha = new Date();
		CntxMantCorrelativoActiva context = FACTORY.cntxMantCorrelativoActiva();
		FACTORY.initialize(EVENTBUS);
		CorrelativoActivaProxy bean = context
				.create(CorrelativoActivaProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setCodeCorrelativo(this.bean.getIdCorrelativo());
		bean.setCodeCorrelativoActiva(this.bean
				.getCodeCorrelativoActivaActual());
		Request<Boolean> request = context.actualizarCorrelativoActiva(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Desactivado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Desactivado correctamente");
	            	not.showPopup();
					goToUiCorrelativo();
				} else {
					//Window.alert("Error al desactivar");
					Notification not=new Notification(Notification.ALERT,"Error al desactivar");
	            	not.showPopup();
				}
			}
		});

	}

	@Override
	public void goToBack() {
		uiHomeCorrelativo.getContainer().showWidget(0);
		uiHomeCorrelativo.getUiCorrelativo().loadTable();
	}

	@Override
	public void goToUiCorrelativo() {
		cleanForm();
		uiHomeCorrelativo.getContainer().showWidget(0);
		uiHomeCorrelativo.getUiCorrelativo().loadTable();
	}
}
