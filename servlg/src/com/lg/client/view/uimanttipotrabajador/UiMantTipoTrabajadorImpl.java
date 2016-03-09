package com.lg.client.view.uimanttipotrabajador;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoTrabajadorProxy;
import com.lg.client.requestfactory.CntxMantTipoTrabajador;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitipotrabajador.UiHomeTipoTrabajador;

public class UiMantTipoTrabajadorImpl extends UiMantTipoTrabajador {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTipoTrabajador uiHomeTipoTrabajador;

	public UiMantTipoTrabajadorImpl(UiHomeTipoTrabajador uiHomeTipoTrabajador) {
		this.uiHomeTipoTrabajador = uiHomeTipoTrabajador;
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantTipoTrabajador context = FACTORY.cntxMantTipoTrabajador();
		FACTORY.initialize(EVENTBUS);
		TipoTrabajadorProxy bean = context.create(TipoTrabajadorProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setIdTipoTrabajador(txtAbreviatura.getText().toUpperCase());
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
		Request<Boolean> request = context.insertarTipoTrabajador(bean);
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
		CntxMantTipoTrabajador context = FACTORY.cntxMantTipoTrabajador();
		FACTORY.initialize(EVENTBUS);
		TipoTrabajadorProxy bean = context.create(TipoTrabajadorProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setCodeTipoTrabajador(this.bean.getIdTipoTrabajador());
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
		Request<Boolean> request = context.actualizarTipoTrabajador(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
					not.showPopup();
					goToUiTipoTrabajador();
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
		CntxMantTipoTrabajador context = FACTORY.cntxMantTipoTrabajador();
		FACTORY.initialize(EVENTBUS);
		TipoTrabajadorProxy bean = context.create(TipoTrabajadorProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setCodeTipoTrabajador(this.bean.getIdTipoTrabajador());
		bean.setAbreviatura(this.bean.getAbreviatura());
		Request<Boolean> request = context.eliminarTipoTrabajador(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
					not.showPopup();
					goToUiTipoTrabajador();
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
		uiHomeTipoTrabajador.getContainer().showWidget(0);
		uiHomeTipoTrabajador.getUiTipoTrabajador().loadTable();
	}

	@Override
	public void goToUiTipoTrabajador() {
		cleanForm();
		uiHomeTipoTrabajador.getContainer().showWidget(0);
		uiHomeTipoTrabajador.getUiTipoTrabajador().loadTable();
	}
}
