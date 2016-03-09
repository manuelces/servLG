package com.lg.client.view.uitrabajador;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantTrabajador;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitrabajador.UiTrabajador;
import com.lg.client.view.uitrabajador.UiHomeTrabajador;

public class UiTrabajadorImpl extends UiTrabajador {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTrabajador uiHomeTrabajador;

	public UiTrabajadorImpl(UiHomeTrabajador uiHomeTrabajador) {
		this.uiHomeTrabajador = uiHomeTrabajador;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantTrabajador context = FACTORY.cntxMantTrabajador();
		FACTORY.initialize(EVENTBUS);
		Request<List<TrabajadorProxy>> request = context.listarTrabajador().with("beanTipoTrabajador");
		request.fire(new Receiver<List<TrabajadorProxy>>() {

			@Override
			public void onSuccess(List<TrabajadorProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeTrabajador.getContainer().showWidget(1);
		uiHomeTrabajador.getUiMantTrabajador().setModo(
				UiMantenimiento.MODOINSERTAR);
		uiHomeTrabajador.getUiMantTrabajador().setBean(null);
		uiHomeTrabajador.getUiMantTrabajador().loadFields();
		uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
		uiHomeTrabajador.getUiMantTrabajador().cleanForm();
		uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		TrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTrabajador.getContainer().showWidget(1);
			uiHomeTrabajador.getUiMantTrabajador().setModo(
					UiMantenimiento.MODOUPDATE);
			uiHomeTrabajador.getUiMantTrabajador().setBean(bean);
			uiHomeTrabajador.getUiMantTrabajador().loadFields();
			uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
			uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		TrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTrabajador.getContainer().showWidget(1);
			uiHomeTrabajador.getUiMantTrabajador().setModo(
					UiMantenimiento.MODODELETE);
			uiHomeTrabajador.getUiMantTrabajador().setBean(bean);
			uiHomeTrabajador.getUiMantTrabajador().loadFields();
			uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
			uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		TrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTrabajador.getContainer().showWidget(1);
			uiHomeTrabajador.getUiMantTrabajador().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeTrabajador.getUiMantTrabajador().setBean(bean);
			uiHomeTrabajador.getUiMantTrabajador().loadFields();
			uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
			uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}
	
	@Override
	public void desactivarTrabajador() {
		// TODO Auto-generated method stub
		TrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstadoActual()==1) {
			uiHomeTrabajador.getContainer().showWidget(1);
			uiHomeTrabajador.getUiMantTrabajador().setModo(
					"DESACTIVAR");
			uiHomeTrabajador.getUiMantTrabajador().setBean(bean);
			uiHomeTrabajador.getUiMantTrabajador().loadFields();
			uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
			uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado activado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de</br>la tabla con estado activado");
			not.showPopup();
		}
	}

	@Override
	public void activarTrabajador() {
		// TODO Auto-generated method stub
		TrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null && bean.getEstadoActual()==0) {
			uiHomeTrabajador.getContainer().showWidget(1);
			uiHomeTrabajador.getUiMantTrabajador().setModo(
					"ACTIVAR");
			uiHomeTrabajador.getUiMantTrabajador().setBean(bean);
			uiHomeTrabajador.getUiMantTrabajador().loadFields();
			uiHomeTrabajador.getUiMantTrabajador().loadTipoTrabajador();
			uiHomeTrabajador.getUiMantTrabajador().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla con estado desactivado");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de</br>la tabla con estado desactivado");
			not.showPopup();
		}
	}
}
