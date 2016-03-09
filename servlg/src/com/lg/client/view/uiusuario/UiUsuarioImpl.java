package com.lg.client.view.uiusuario;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantUsuario;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uiusuario.UiUsuario;
import com.lg.client.view.uiusuario.UiHomeUsuario;

public class UiUsuarioImpl extends UiUsuario {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeUsuario uiHomeUsuario;

	public UiUsuarioImpl(UiHomeUsuario uiHomeUsuario) {
		this.uiHomeUsuario = uiHomeUsuario;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantUsuario context = FACTORY.cntxMantUsuario();
		FACTORY.initialize(EVENTBUS);
		Request<List<UsuarioProxy>> request = context.listarUsuario().with(
				"beanTrabajador");
		request.fire(new Receiver<List<UsuarioProxy>>() {

			@Override
			public void onSuccess(List<UsuarioProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeUsuario.getContainer().showWidget(1);
		uiHomeUsuario.getUiMantUsuario().setModo(UiMantenimiento.MODOINSERTAR);
		uiHomeUsuario.getUiMantUsuario().setBean(null);
		uiHomeUsuario.getUiMantUsuario().loadFields();
		// uiHomeUsuario.getUiMantUsuario().loadTrabajador();
		uiHomeUsuario.getUiMantUsuario().cleanForm();
		uiHomeUsuario.getUiMantUsuario().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		/*
		 * UsuarioProxy bean = grid.getSelectionModel().getSelectedObject(); if
		 * (bean != null) { uiHomeTrabajador.getContainer().showWidget(1);
		 * uiHomeTrabajador.getUiMantTrabajador().setModo(
		 * UiMantenimiento.MODOUPDATE);
		 * uiHomeTrabajador.getUiMantTrabajador().setBean(bean);
		 * uiHomeTrabajador.getUiMantTrabajador().loadFields();
		 * uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
		 * uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh(); } else
		 * { Window.alert("Seleccione un registro de la tabla"); }
		 */
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		UsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeUsuario.getContainer().showWidget(1);
			uiHomeUsuario.getUiMantUsuario()
					.setModo(UiMantenimiento.MODODELETE);
			uiHomeUsuario.getUiMantUsuario().setBean(bean);
			uiHomeUsuario.getUiMantUsuario().loadFields();
			// uiHomeUsuario.getUiMantUsuario().loadTrabajador();
			uiHomeUsuario.getUiMantUsuario().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		UsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeUsuario.getContainer().showWidget(1);
			uiHomeUsuario.getUiMantUsuario().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeUsuario.getUiMantUsuario().setBean(bean);
			uiHomeUsuario.getUiMantUsuario().loadFields();
			// uiHomeUsuario.getUiMantUsuario().loadTrabajador();
			uiHomeUsuario.getUiMantUsuario().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void desactivarUsuario() {
		// TODO Auto-generated method stub
		UsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstadoActual() == 1) {
			uiHomeUsuario.getContainer().showWidget(1);
			uiHomeUsuario.getUiMantUsuario().setModo("DESACTIVAR");
			uiHomeUsuario.getUiMantUsuario().setBean(bean);
			uiHomeUsuario.getUiMantUsuario().loadFields();
			// uiHomeUsuario.getUiMantUsuario().loadTipoTrabajador();
			uiHomeUsuario.getUiMantUsuario().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado activado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de</br>la tabla con estado activado");
			not.showPopup();
		}
	}

	@Override
	public void activarUsuario() {
		// TODO Auto-generated method stub
		UsuarioProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstadoActual() == 0) {
			uiHomeUsuario.getContainer().showWidget(1);
			uiHomeUsuario.getUiMantUsuario().setModo("ACTIVAR");
			uiHomeUsuario.getUiMantUsuario().setBean(bean);
			uiHomeUsuario.getUiMantUsuario().loadFields();
			// uiHomeUsuario.getUiMantUsuario().loadTipoTrabajador();
			uiHomeUsuario.getUiMantUsuario().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado desactivado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de</br>la tabla con estado desactivado");
			not.showPopup();
		}
	}
}
