package com.lg.client.view.uimanttrabajador;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoTrabajadorProxy;
import com.lg.client.beanproxy.TrabajadorActivaProxy;
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.client.requestfactory.CntxMantTipoTrabajador;
import com.lg.client.requestfactory.CntxMantTrabajador;
import com.lg.client.requestfactory.CntxMantTrabajadorActiva;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitrabajador.UiHomeTrabajador;
import com.lg.client.view.uimanttrabajador.UiMantTrabajador;

public class UiMantTrabajadorImpl extends UiMantTrabajador {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeTrabajador uiHomeTrabajador;

	public UiMantTrabajadorImpl(UiHomeTrabajador uiHomeTrabajador) {
		this.uiHomeTrabajador = uiHomeTrabajador;
		loadTipoTrabajador();
	}

	@Override
	public void loadTipoTrabajador() {
		// TODO Auto-generated method stub
		CntxMantTipoTrabajador context = FACTORY.cntxMantTipoTrabajador();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoTrabajadorProxy>> request = context
				.listarTipoTrabajador();
		request.fire(new Receiver<List<TipoTrabajadorProxy>>() {

			@Override
			public void onSuccess(List<TipoTrabajadorProxy> response) {
				lstTipoTrabajador.setData(response);
				if (bean != null) {
					lstTipoTrabajador.setSelectedItem(bean
							.getBeanTipoTrabajador().getDescripcion());
				}
			}
		});
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantTrabajador context = FACTORY.cntxMantTrabajador();
		FACTORY.initialize(EVENTBUS);
		TrabajadorProxy bean = context.create(TrabajadorProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setPaterno(txtPaterno.getText().toUpperCase());
		bean.setMaterno(txtMaterno.getText().toUpperCase());
		bean.setNombre(txtNombre.getText().toUpperCase());
		bean.setDni(txtDni.getText().toUpperCase());
		bean.setIdTrabajador(lstTipoTrabajador.getSelectedItem()
				.getIdTipoTrabajador());
		bean.setBeanTipoTrabajador(lstTipoTrabajador.getSelectedItem());
		bean.setCodeTipoTrabajador(lstTipoTrabajador.getSelectedItem()
				.getIdTipoTrabajador());
		Request<Boolean> request = context.insertarTrabajador(bean);
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
		CntxMantTrabajador context = FACTORY.cntxMantTrabajador();
		FACTORY.initialize(EVENTBUS);
		TrabajadorProxy bean = context.create(TrabajadorProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setPaterno(txtPaterno.getText().toUpperCase());
		bean.setMaterno(txtMaterno.getText().toUpperCase());
		bean.setNombre(txtNombre.getText().toUpperCase());
		bean.setDni(txtDni.getText().toUpperCase());
		bean.setCodeTrabajador(this.bean.getIdTrabajador());
		bean.setBeanTipoTrabajador(lstTipoTrabajador.getSelectedItem());
		bean.setCodeTipoTrabajador(lstTipoTrabajador.getSelectedItem()
				.getIdTipoTrabajador());
		Request<Boolean> request = context.actualizarTrabajador(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
					not.showPopup();
					goToUiTrabajador();
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
		CntxMantTrabajador context = FACTORY.cntxMantTrabajador();
		FACTORY.initialize(EVENTBUS);
		TrabajadorProxy bean = context.create(TrabajadorProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setCodeTrabajador(this.bean.getIdTrabajador());
		bean.setPaterno(txtPaterno.getText().toUpperCase());
		bean.setMaterno(txtMaterno.getText().toUpperCase());
		bean.setNombre(txtNombre.getText().toUpperCase());
		bean.setDni(txtDni.getText().toUpperCase());
		Request<Boolean> request = context.eliminarTrabajador(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
					not.showPopup();
					goToUiTrabajador();
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
		CntxMantTrabajadorActiva context = FACTORY.cntxMantTrabajadorActiva();
		FACTORY.initialize(EVENTBUS);
		TrabajadorActivaProxy bean = context
				.create(TrabajadorActivaProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setCodeTrabajadorActiva(this.bean.getIdTrabajador());		
		bean.setCodeTrabajador(this.bean.getIdTrabajador());			
		Request<Boolean> request = context.insertarTrabajadorActiva(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					//Window.alert("Activado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Activado correctamente");
					not.showPopup();
					goToUiTrabajador();
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
		CntxMantTrabajadorActiva context = FACTORY.cntxMantTrabajadorActiva();
		FACTORY.initialize(EVENTBUS);
		TrabajadorActivaProxy bean = context
				.create(TrabajadorActivaProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setCodeTrabajador(this.bean.getIdTrabajador());
		bean.setCodeTrabajadorActiva(this.bean.getCodeTrabajadorActivaActual());
		Request<Boolean> request = context.actualizarTrabajadorActiva(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Desactivado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Desactivado correctamente");
					not.showPopup();
					goToUiTrabajador();
				} else {
					//Window.alert("Error al desactivar");
					Notification not=new Notification(Notification.ALERT,"Error al desactivar");
					not.showPopup();
				}
			}
		});
	}

	@Override
	public void goToBack() {
		uiHomeTrabajador.getContainer().showWidget(0);
		uiHomeTrabajador.getUiTrabajador().loadTable();
	}

	@Override
	public void goToUiTrabajador() {
		cleanForm();
		uiHomeTrabajador.getContainer().showWidget(0);
		uiHomeTrabajador.getUiTrabajador().loadTable();
	}
}
