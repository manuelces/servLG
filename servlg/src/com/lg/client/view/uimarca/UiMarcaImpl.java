package com.lg.client.view.uimarca;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.MarcaProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantMarca;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiMarcaImpl extends UiMarca {

	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeMarca uiHomeMarca;

	public UiMarcaImpl(UiHomeMarca uiHomeMarca) {
		this.uiHomeMarca = uiHomeMarca;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantMarca context = FACTORY.cntxMantMarca();
		FACTORY.initialize(EVENTBUS);
		Request<List<MarcaProxy>> request = context.listarMarca();
		request.fire(new Receiver<List<MarcaProxy>>() {

			@Override
			public void onSuccess(List<MarcaProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeMarca.getContainer().showWidget(1);
		uiHomeMarca.getUiMantMarca().setModo(UiMantenimiento.MODOINSERTAR);
		uiHomeMarca.getUiMantMarca().setBean(null);
		uiHomeMarca.getUiMantMarca().loadFields();
		uiHomeMarca.getUiMantMarca().cleanForm();
		uiHomeMarca.getUiMantMarca().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		MarcaProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeMarca.getContainer().showWidget(1);
			uiHomeMarca.getUiMantMarca().setModo(UiMantenimiento.MODOUPDATE);
			uiHomeMarca.getUiMantMarca().setBean(bean);
			uiHomeMarca.getUiMantMarca().loadFields();
			uiHomeMarca.getUiMantMarca().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		MarcaProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeMarca.getContainer().showWidget(1);
			uiHomeMarca.getUiMantMarca().setModo(UiMantenimiento.MODODELETE);
			uiHomeMarca.getUiMantMarca().setBean(bean);
			uiHomeMarca.getUiMantMarca().loadFields();
			uiHomeMarca.getUiMantMarca().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		MarcaProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeMarca.getContainer().showWidget(1);
			uiHomeMarca.getUiMantMarca().setModo(UiMantenimiento.MODODETALLE);
			uiHomeMarca.getUiMantMarca().setBean(bean);
			uiHomeMarca.getUiMantMarca().loadFields();
			uiHomeMarca.getUiMantMarca().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

}
