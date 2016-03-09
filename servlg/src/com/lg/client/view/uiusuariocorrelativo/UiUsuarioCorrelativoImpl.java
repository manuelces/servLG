package com.lg.client.view.uiusuariocorrelativo;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.UsuarioCorrelativoProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantUsuarioCorrelativo;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiUsuarioCorrelativoImpl extends UiUsuarioCorrelativo {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeUsuarioCorrelativo uiHomeUsuarioCorrelativo;

	public UiUsuarioCorrelativoImpl(
			UiHomeUsuarioCorrelativo uiHomeUsuarioCorrelativo) {
		this.uiHomeUsuarioCorrelativo = uiHomeUsuarioCorrelativo;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantUsuarioCorrelativo context = FACTORY
				.cntxMantUsuarioCorrelativo();
		FACTORY.initialize(EVENTBUS);
		Request<List<UsuarioCorrelativoProxy>> request = context
				.listarUsuarioCorrelativo().with("beanCorrelativo",
						"beanUsuario");
		request.fire(new Receiver<List<UsuarioCorrelativoProxy>>() {

			@Override
			public void onSuccess(List<UsuarioCorrelativoProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeUsuarioCorrelativo.getContainer().showWidget(1);
		uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setModo(
				UiMantenimiento.MODOINSERTAR);
		uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setBean(null);
		uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().loadFields();
		uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
				.loadTipoDocumento();
		uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().cleanForm();
		uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().scrollPanel
				.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		UsuarioCorrelativoProxy bean = grid.getSelectionModel()
				.getSelectedObject();
		if (bean != null) {
			uiHomeUsuarioCorrelativo.getContainer().showWidget(1);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setModo(
					UiMantenimiento.MODOUPDATE);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.setBean(bean);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().loadFields();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.loadTipoDocumento();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().scrollPanel
					.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		UsuarioCorrelativoProxy bean = grid.getSelectionModel()
				.getSelectedObject();
		if (bean != null) {
			uiHomeUsuarioCorrelativo.getContainer().showWidget(1);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setModo(
					UiMantenimiento.MODODELETE);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.setBean(bean);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().loadFields();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.loadTipoDocumento();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().scrollPanel
					.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		UsuarioCorrelativoProxy bean = grid.getSelectionModel()
				.getSelectedObject();
		if (bean != null) {
			uiHomeUsuarioCorrelativo.getContainer().showWidget(1);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.setBean(bean);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().loadFields();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.loadTipoDocumento();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().scrollPanel
					.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void desactivarUsuarioCorrelativo() {
		// TODO Auto-generated method stub
		UsuarioCorrelativoProxy bean = grid.getSelectionModel()
				.getSelectedObject();
		if (bean != null && bean.getEstadoActual() == 1) {
			uiHomeUsuarioCorrelativo.getContainer().showWidget(1);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setModo(
					"DESACTIVAR");
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.setBean(bean);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().loadFields();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.loadTipoDocumento();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().scrollPanel
					.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado activado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de</br>la tabla con estado activado");
			not.showPopup();
		}
	}

	@Override
	public void activarUsuarioCorrelativo() {
		// TODO Auto-generated method stub
		UsuarioCorrelativoProxy bean = grid.getSelectionModel()
				.getSelectedObject();
		if (bean != null && bean.getEstadoActual() == 0) {
			uiHomeUsuarioCorrelativo.getContainer().showWidget(1);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().setModo(
					"ACTIVAR");
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.setBean(bean);
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().loadFields();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo()
					.loadTipoDocumento();
			uiHomeUsuarioCorrelativo.getUiMantUsuarioCorrelativo().scrollPanel
					.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado desactivado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de</br>la tabla con estado desactivado");
			not.showPopup();
		}
	}
}
