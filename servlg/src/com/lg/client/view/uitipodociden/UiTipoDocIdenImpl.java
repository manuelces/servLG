package com.lg.client.view.uitipodociden;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoDocIdenProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantTipoDocIden;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitipodociden.UiHomeTipoDocIden;

public class UiTipoDocIdenImpl extends UiTipoDocIden {

	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTipoDocIden uiHomeTipoDocIden;

	public UiTipoDocIdenImpl(UiHomeTipoDocIden uiHomeTipoDocIden) {
		this.uiHomeTipoDocIden = uiHomeTipoDocIden;
		loadTable();
	}

	@Override
	public void loadTable() {
		CntxMantTipoDocIden context = FACTORY.cntxMantTipoDocIden();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoDocIdenProxy>> request = context.listarTipoDocIden();
		request.fire(new Receiver<List<TipoDocIdenProxy>>() {

			@Override
			public void onSuccess(List<TipoDocIdenProxy> response) {
				grid.getSelectionModel().clear();
				grid.setData(response);
			}
		});
	}

	@Override
	public void showUIOper1() {
		// TODO Auto-generated method stub
		uiHomeTipoDocIden.getContainer().showWidget(1);
		uiHomeTipoDocIden.getUiMantTipoDocIden().setModo(
				UiMantenimiento.MODOINSERTAR);
		uiHomeTipoDocIden.getUiMantTipoDocIden().setBean(null);
		uiHomeTipoDocIden.getUiMantTipoDocIden().loadFields();
		uiHomeTipoDocIden.getUiMantTipoDocIden().cleanForm();
		uiHomeTipoDocIden.getUiMantTipoDocIden().scrollPanel.refresh();
	}

	@Override
	public void showUIOper2() {
		// TODO Auto-generated method stub
		TipoDocIdenProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoDocIden.getContainer().showWidget(1);
			uiHomeTipoDocIden.getUiMantTipoDocIden().setModo(
					UiMantenimiento.MODOUPDATE);
			uiHomeTipoDocIden.getUiMantTipoDocIden().setBean(bean);
			uiHomeTipoDocIden.getUiMantTipoDocIden().loadFields();
			uiHomeTipoDocIden.getUiMantTipoDocIden().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper3() {
		// TODO Auto-generated method stub
		TipoDocIdenProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoDocIden.getContainer().showWidget(1);
			uiHomeTipoDocIden.getUiMantTipoDocIden().setModo(
					UiMantenimiento.MODODELETE);
			uiHomeTipoDocIden.getUiMantTipoDocIden().setBean(bean);
			uiHomeTipoDocIden.getUiMantTipoDocIden().loadFields();
			uiHomeTipoDocIden.getUiMantTipoDocIden().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

	@Override
	public void showUIOper4() {
		// TODO Auto-generated method stub
		TipoDocIdenProxy bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			uiHomeTipoDocIden.getContainer().showWidget(1);
			uiHomeTipoDocIden.getUiMantTipoDocIden().setModo(
					UiMantenimiento.MODODETALLE);
			uiHomeTipoDocIden.getUiMantTipoDocIden().setBean(bean);
			uiHomeTipoDocIden.getUiMantTipoDocIden().loadFields();
			uiHomeTipoDocIden.getUiMantTipoDocIden().scrollPanel.refresh();
		} else {
			//Window.alert("Seleccione un registro de la tabla");
			Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
		}
	}

}
