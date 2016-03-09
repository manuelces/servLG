package com.lg.client.view.uielectro;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ElectroProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantElectro;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiElectroImpl extends UiElectro {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeElectro uiHomeElectro;

	public UiElectroImpl(UiHomeElectro uiHomeElectro) {
		this.uiHomeElectro = uiHomeElectro;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantElectro context = FACTORY.cntxMantElectro();
		FACTORY.initialize(EVENTBUS);
		Request<List<ElectroProxy>> request = context.listarElectro();
		request.fire(new Receiver<List<ElectroProxy>>() {

			@Override
			public void onSuccess(List<ElectroProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeElectro.getContainer().showWidget(1);
		uiHomeElectro.getUiMantElectro().setModo(UiMantenimiento.MODOINSERTAR);
		uiHomeElectro.getUiMantElectro().setBean(null);
		uiHomeElectro.getUiMantElectro().loadFields();
		uiHomeElectro.getUiMantElectro().cleanForm();
		uiHomeElectro.getUiMantElectro().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		ElectroProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeElectro.getContainer().showWidget(1);
			uiHomeElectro.getUiMantElectro()
					.setModo(UiMantenimiento.MODOUPDATE);
			uiHomeElectro.getUiMantElectro().setBean(bean);
			uiHomeElectro.getUiMantElectro().loadFields();
			uiHomeElectro.getUiMantElectro().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		ElectroProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeElectro.getContainer().showWidget(1);
			uiHomeElectro.getUiMantElectro()
					.setModo(UiMantenimiento.MODODELETE);
			uiHomeElectro.getUiMantElectro().setBean(bean);
			uiHomeElectro.getUiMantElectro().loadFields();
			uiHomeElectro.getUiMantElectro().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		ElectroProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeElectro.getContainer().showWidget(1);
			uiHomeElectro.getUiMantElectro().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeElectro.getUiMantElectro().setBean(bean);
			uiHomeElectro.getUiMantElectro().loadFields();
			uiHomeElectro.getUiMantElectro().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
		}
	}
}
