package com.lg.client.view.uimantusuario;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.UsuarioActivaProxy;
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.client.requestfactory.CntxMantUsuario;
import com.lg.client.requestfactory.CntxMantUsuarioActiva;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uiusuario.UiHomeUsuario;
import com.lg.client.view.uimantusuario.UiMantUsuario;

public class UiMantUsuarioImpl extends UiMantUsuario {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeUsuario uiHomeUsuario;

	public UiMantUsuarioImpl(UiHomeUsuario uiHomeUsuario) {
		this.uiHomeUsuario = uiHomeUsuario;
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantUsuario context = FACTORY.cntxMantUsuario();
		FACTORY.initialize(EVENTBUS);
		UsuarioProxy bean = context.create(UsuarioProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setIdUsuario(beanTrabajador.getIdTrabajador());
		bean.setLogin(txtLogin.getText().toUpperCase());
		bean.setClave(txtClave.getText().toUpperCase());
		// bean.setBeanTrabajador(beanTrabajador);
		bean.setCodeTrabajador(beanTrabajador.getIdTrabajador());
		// bean.setIdTrabajador(lstTipoTrabajador.getSelectedItem().getIdTipoTrabajador());
		Request<Boolean> request = context.insertarUsuario(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					cleanForm();
					//Window.alert("Insertado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
					not.showPopup();
				} else {
					Window.alert("Error al insertar");
					Notification not=new Notification(Notification.ALERT,"Error al insertar");
					not.showPopup();
				}
			}
		});
	}

	@Override
	public void processActualizar() {
	}

	@Override
	public void processEliminar() {
		Date fecha = new Date();
		CntxMantUsuario context = FACTORY.cntxMantUsuario();
		FACTORY.initialize(EVENTBUS);
		UsuarioProxy bean = context.create(UsuarioProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setCodeUsuario(this.bean.getIdUsuario());
		bean.setLogin(txtLogin.getText());
		bean.setClave(txtClave.getText());
		Request<Boolean> request = context.eliminarUsuario(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
					not.showPopup();
					goToUiUsuario();
				} else {
					//Window.alert("Error al eliminar");
					Notification not=new Notification(Notification.ALERT,"Error al eliminar");
					not.showPopup();
				}
			}
		});
	}

	@Override
	public void processActivar() {
		// TODO Auto-generated method stub
		Date fecha = new Date();
		CntxMantUsuarioActiva context = FACTORY.cntxMantUsuarioActiva();
		FACTORY.initialize(EVENTBUS);
		UsuarioActivaProxy bean = context.create(UsuarioActivaProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setCodeUsuarioActiva(this.bean.getIdUsuario());
		bean.setCodeUsuario(this.bean.getIdUsuario());
		Request<Boolean> request = context.insertarUsuarioActiva(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					//Window.alert("Activado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Activado correctamente");
					not.showPopup();
					goToUiUsuario();
				} else {
					//Window.alert("Error al activar");
					Notification not=new Notification(Notification.ALERT,"Error al activar");
					not.showPopup();
					
				}
			}

		});
	}

	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub
		Date fecha = new Date();
		CntxMantUsuarioActiva context = FACTORY.cntxMantUsuarioActiva();
		FACTORY.initialize(EVENTBUS);
		UsuarioActivaProxy bean = context.create(UsuarioActivaProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setCodeUsuario(this.bean.getIdUsuario());
		bean.setCodeUsuarioActiva(this.bean.getCodeUsuarioActivaActual());
		Request<Boolean> request = context.actualizarUsuarioActiva(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Desactivado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Desactivado correctamente");
					not.showPopup();
					goToUiUsuario();
				} else {
					//Window.alert("Error al desactivar");
					Notification not=new Notification(Notification.ALERT,"Error al desactivar");
					not.showPopup();
				}
			}
		});
	}

	public void goToBack() {
		uiHomeUsuario.getContainer().showWidget(0);
		uiHomeUsuario.getUiUsuario().loadTable();
	}

	@Override
	public void goToUiUsuario() {
		cleanForm();
		uiHomeUsuario.getContainer().showWidget(0);
		uiHomeUsuario.getUiUsuario().loadTable();
	}
}
