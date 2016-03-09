package com.lg.client.view.uimanttipodocumento;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.client.requestfactory.CntxMantTipoDocumento;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitipodocumento.UiHomeTipoDocumento;

public class UiMantTipoDocumentoImpl extends UiMantTipoDocumento {

	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTipoDocumento uiHomeTipoDocumento;

	public UiMantTipoDocumentoImpl(UiHomeTipoDocumento uiHomeTipoDocumento) {
		this.uiHomeTipoDocumento = uiHomeTipoDocumento;
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantTipoDocumento context = FACTORY.cntxMantTipoDocumento();
		FACTORY.initialize(EVENTBUS);
		TipoDocumentoProxy bean = context.create(TipoDocumentoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		//bean.setId(txtIdTipoDoc.getText().toUpperCase());
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setAbrev(txtAbreviatura.getText().toUpperCase());
		bean.setIdTipoDoc(txtIdTipoDoc.getText().toUpperCase());
		Request<Boolean> request = context.insertarTipoDocumento(bean);
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
		CntxMantTipoDocumento context = FACTORY.cntxMantTipoDocumento();
		FACTORY.initialize(EVENTBUS);
		TipoDocumentoProxy bean = context.create(TipoDocumentoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setAbrev(txtAbreviatura.getText().toUpperCase());
		bean.setIdTipoDoc(txtIdTipoDoc.getText().toUpperCase());
		//bean.setCodeTipoDoc(this.bean.getId());
		Request<Boolean> request = context.actualizarTipoDocumento(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
					not.showPopup();
					goToUiTipoDocumento();
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
		CntxMantTipoDocumento context = FACTORY.cntxMantTipoDocumento();
		FACTORY.initialize(EVENTBUS);
		TipoDocumentoProxy bean = context.create(TipoDocumentoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setIdTipoDoc(this.bean.getIdTipoDoc());
		bean.setAbrev(this.bean.getAbrev());
		Request<Boolean> request = context.eliminarTipoDocumento(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
					not.showPopup();
					goToUiTipoDocumento();
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
		uiHomeTipoDocumento.getContainer().showWidget(0);
		uiHomeTipoDocumento.getUiTipoDocumento().loadTable();
	}

	@Override
	public void goToUiTipoDocumento() {
		cleanForm();
		uiHomeTipoDocumento.getContainer().showWidget(0);
		uiHomeTipoDocumento.getUiTipoDocumento().loadTable();
	}

}
