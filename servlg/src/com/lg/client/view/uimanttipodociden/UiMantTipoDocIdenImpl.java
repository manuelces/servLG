package com.lg.client.view.uimanttipodociden;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoDocIdenProxy;
import com.lg.client.requestfactory.CntxMantTipoDocIden;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitipodociden.UiHomeTipoDocIden;

public class UiMantTipoDocIdenImpl extends UiMantTipoDocIden {

	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTipoDocIden uiHomeTipoDocIden;

	public UiMantTipoDocIdenImpl(UiHomeTipoDocIden uiHomeTipoDocIden) {
		this.uiHomeTipoDocIden = uiHomeTipoDocIden;
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantTipoDocIden context = FACTORY.cntxMantTipoDocIden();
		FACTORY.initialize(EVENTBUS);
		TipoDocIdenProxy bean = context.create(TipoDocIdenProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");	
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setAbrev(txtAbreviatura.getText().toUpperCase());
		bean.setIdTipoDoc(txtIdTipoDoc.getText().toUpperCase());
		Request<Boolean> request = context.insertarTipoDocIden(bean);
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
		CntxMantTipoDocIden context = FACTORY.cntxMantTipoDocIden();
		FACTORY.initialize(EVENTBUS);
		TipoDocIdenProxy bean = context.create(TipoDocIdenProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setAbrev(txtAbreviatura.getText().toUpperCase());
		bean.setIdTipoDoc(this.bean.getIdTipoDoc());
		//bean.setCodeTipoDoc(this.bean.getId());
		Request<Boolean> request = context.actualizarTipoDocIden(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
					not.showPopup();
					goToUiTipoDocIden();
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
		CntxMantTipoDocIden context = FACTORY.cntxMantTipoDocIden();
		FACTORY.initialize(EVENTBUS);
		TipoDocIdenProxy bean = context.create(TipoDocIdenProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		//bean.setIdTipoDoc(this.bean.getIdTipoDoc());
		//bean.setAbrev(this.bean.getAbrev());
		bean.setIdTipoDoc(this.bean.getIdTipoDoc());
		Request<Boolean> request = context.eliminarTipoDocIden(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
					not.showPopup();
					goToUiTipoDocIden();
				} else {
					//Window.alert("Error al eliminar");
					Notification not=new Notification(Notification.ALERT,"Error al eliminar");
					not.showPopup();
				}
			}
		});
	}

	@Override
	public void goToBack() {
		uiHomeTipoDocIden.getContainer().showWidget(0);
		uiHomeTipoDocIden.getUiTipoDocIden().loadTable();
	}

	@Override
	public void goToUiTipoDocIden() {
		cleanForm();
		uiHomeTipoDocIden.getContainer().showWidget(0);
		uiHomeTipoDocIden.getUiTipoDocIden().loadTable();
	}

}
