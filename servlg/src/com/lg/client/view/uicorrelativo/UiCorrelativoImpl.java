package com.lg.client.view.uicorrelativo;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.CorrelativoProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantCorrelativo;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiCorrelativoImpl extends UiCorrelativo {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeCorrelativo uiHomeCorrelativo;

	public UiCorrelativoImpl(UiHomeCorrelativo uiHomeCorrelativo) {
		this.uiHomeCorrelativo = uiHomeCorrelativo;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantCorrelativo context = FACTORY.cntxMantCorrelativo();
		FACTORY.initialize(EVENTBUS);
		Request<List<CorrelativoProxy>> request = context.listarCorrelativo()
				.with("beanTipoDocumento");
		request.fire(new Receiver<List<CorrelativoProxy>>() {

			@Override
			public void onSuccess(List<CorrelativoProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeCorrelativo.getContainer().showWidget(1);
		uiHomeCorrelativo.getUiMantCorrelativo().setModo(
				UiMantenimiento.MODOINSERTAR);
		uiHomeCorrelativo.getUiMantCorrelativo().setBean(null);
		uiHomeCorrelativo.getUiMantCorrelativo().loadFields();
		uiHomeCorrelativo.getUiMantCorrelativo().loadTipoDocumento();
		uiHomeCorrelativo.getUiMantCorrelativo().cleanForm();
		uiHomeCorrelativo.getUiMantCorrelativo().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		CorrelativoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeCorrelativo.getContainer().showWidget(1);
			uiHomeCorrelativo.getUiMantCorrelativo().setModo(
					UiMantenimiento.MODOUPDATE);
			uiHomeCorrelativo.getUiMantCorrelativo().setBean(bean);
			uiHomeCorrelativo.getUiMantCorrelativo().loadFields();
			uiHomeCorrelativo.getUiMantCorrelativo().loadTipoDocumento();
			uiHomeCorrelativo.getUiMantCorrelativo().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		CorrelativoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeCorrelativo.getContainer().showWidget(1);
			uiHomeCorrelativo.getUiMantCorrelativo().setModo(
					UiMantenimiento.MODODELETE);
			uiHomeCorrelativo.getUiMantCorrelativo().setBean(bean);
			uiHomeCorrelativo.getUiMantCorrelativo().loadFields();
			uiHomeCorrelativo.getUiMantCorrelativo().loadTipoDocumento();
			uiHomeCorrelativo.getUiMantCorrelativo().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		CorrelativoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeCorrelativo.getContainer().showWidget(1);
			uiHomeCorrelativo.getUiMantCorrelativo().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeCorrelativo.getUiMantCorrelativo().setBean(bean);
			uiHomeCorrelativo.getUiMantCorrelativo().loadFields();
			uiHomeCorrelativo.getUiMantCorrelativo().loadTipoDocumento();
			uiHomeCorrelativo.getUiMantCorrelativo().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}

	@Override
	public void desactivarCorrelativo() {
		// TODO Auto-generated method stub
		CorrelativoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstadoActual() == 1) {
			uiHomeCorrelativo.getContainer().showWidget(1);
			uiHomeCorrelativo.getUiMantCorrelativo().setModo("DESACTIVAR");
			uiHomeCorrelativo.getUiMantCorrelativo().setBean(bean);
			uiHomeCorrelativo.getUiMantCorrelativo().loadFields();
			uiHomeCorrelativo.getUiMantCorrelativo().loadTipoDocumento();
			uiHomeCorrelativo.getUiMantCorrelativo().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado activado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}

	@Override
	public void activarCorrelativo() {
		// TODO Auto-generated method stub
		CorrelativoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstadoActual() == 0) {
			uiHomeCorrelativo.getContainer().showWidget(1);
			uiHomeCorrelativo.getUiMantCorrelativo().setModo("ACTIVAR");
			uiHomeCorrelativo.getUiMantCorrelativo().setBean(bean);
			uiHomeCorrelativo.getUiMantCorrelativo().loadFields();
			uiHomeCorrelativo.getUiMantCorrelativo().loadTipoDocumento();
			uiHomeCorrelativo.getUiMantCorrelativo().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado desactivado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}
}
