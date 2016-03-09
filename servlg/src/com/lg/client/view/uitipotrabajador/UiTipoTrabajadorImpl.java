package com.lg.client.view.uitipotrabajador;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoTrabajadorProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantTipoTrabajador;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiTipoTrabajadorImpl extends UiTipoTrabajador {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTipoTrabajador uiHomeTipoTrabajador;

	public UiTipoTrabajadorImpl(UiHomeTipoTrabajador uiHomeTipoTrabajador) {
		this.uiHomeTipoTrabajador = uiHomeTipoTrabajador;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantTipoTrabajador context = FACTORY.cntxMantTipoTrabajador();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoTrabajadorProxy>> request = context
				.listarTipoTrabajador();
		request.fire(new Receiver<List<TipoTrabajadorProxy>>() {

			@Override
			public void onSuccess(List<TipoTrabajadorProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeTipoTrabajador.getContainer().showWidget(1);
		uiHomeTipoTrabajador.getUiMantTipoTrabajador().setModo(
				UiMantenimiento.MODOINSERTAR);
		uiHomeTipoTrabajador.getUiMantTipoTrabajador().setBean(null);
		uiHomeTipoTrabajador.getUiMantTipoTrabajador().loadFields();
		uiHomeTipoTrabajador.getUiMantTipoTrabajador().cleanForm();
		uiHomeTipoTrabajador.getUiMantTipoTrabajador().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		TipoTrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoTrabajador.getContainer().showWidget(1);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().setModo(
					UiMantenimiento.MODOUPDATE);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().setBean(bean);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().loadFields();
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().scrollPanel
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
		TipoTrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoTrabajador.getContainer().showWidget(1);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().setModo(
					UiMantenimiento.MODODELETE);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().setBean(bean);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().loadFields();
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().scrollPanel
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
		TipoTrabajadorProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoTrabajador.getContainer().showWidget(1);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().setBean(bean);
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().loadFields();
			uiHomeTipoTrabajador.getUiMantTipoTrabajador().scrollPanel
					.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}
}
