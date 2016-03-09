package com.lg.client.view.uitipodocumento;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantTipoDocumento;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitipodocumento.UiHomeTipoDocumento;

public class UiTipoDocumentoImpl extends UiTipoDocumento {

	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTipoDocumento uiHomeTipoDocumento;

	public UiTipoDocumentoImpl(UiHomeTipoDocumento uiHomeTipoDocumento) {
		this.uiHomeTipoDocumento = uiHomeTipoDocumento;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantTipoDocumento context = FACTORY.cntxMantTipoDocumento();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoDocumentoProxy>> request = context
				.listarTipoDocumento();
		request.fire(new Receiver<List<TipoDocumentoProxy>>() {

			@Override
			public void onSuccess(List<TipoDocumentoProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeTipoDocumento.getContainer().showWidget(1);
		uiHomeTipoDocumento.getUiMantTipoDocumento().setModo(
				UiMantenimiento.MODOINSERTAR);
		uiHomeTipoDocumento.getUiMantTipoDocumento().setBean(null);
		uiHomeTipoDocumento.getUiMantTipoDocumento().loadFields();
		uiHomeTipoDocumento.getUiMantTipoDocumento().cleanForm();
		uiHomeTipoDocumento.getUiMantTipoDocumento().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		TipoDocumentoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoDocumento.getContainer().showWidget(1);
			uiHomeTipoDocumento.getUiMantTipoDocumento().setModo(
					UiMantenimiento.MODOUPDATE);
			uiHomeTipoDocumento.getUiMantTipoDocumento().setBean(bean);
			uiHomeTipoDocumento.getUiMantTipoDocumento().loadFields();
			uiHomeTipoDocumento.getUiMantTipoDocumento().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		TipoDocumentoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoDocumento.getContainer().showWidget(1);
			uiHomeTipoDocumento.getUiMantTipoDocumento().setModo(
					UiMantenimiento.MODODELETE);
			uiHomeTipoDocumento.getUiMantTipoDocumento().setBean(bean);
			uiHomeTipoDocumento.getUiMantTipoDocumento().loadFields();
			uiHomeTipoDocumento.getUiMantTipoDocumento().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		TipoDocumentoProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoDocumento.getContainer().showWidget(1);
			uiHomeTipoDocumento.getUiMantTipoDocumento().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeTipoDocumento.getUiMantTipoDocumento().setBean(bean);
			uiHomeTipoDocumento.getUiMantTipoDocumento().loadFields();
			uiHomeTipoDocumento.getUiMantTipoDocumento().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

}
