package com.lg.client.view.uimantusuariocorrelativo;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.CorrelativoProxy;
import com.lg.client.beanproxy.UsuarioCorrelativoProxy;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.client.requestfactory.CntxMantUsuarioCorrelativo;
import com.lg.client.requestfactory.CntxMantTipoDocumento;
import com.lg.client.requestfactory.CntxMantCorrelativo;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uiusuariocorrelativo.UiHomeUsuarioCorrelativo;

public class UiMantUsuarioCorrelativoImpl extends UiMantUsuarioCorrelativo {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeUsuarioCorrelativo uiHomeUsuarioCorrelativo;

	public UiMantUsuarioCorrelativoImpl(
			UiHomeUsuarioCorrelativo uiHomeUsuarioCorrelativo) {
		this.uiHomeUsuarioCorrelativo = uiHomeUsuarioCorrelativo;
		loadTipoDocumento();
	}

	@Override
	public void loadTipoDocumento() {
		// TODO Auto-generated method stub
		CntxMantTipoDocumento context = FACTORY.cntxMantTipoDocumento();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoDocumentoProxy>> request = context
				.listarTipoDocumento();
		request.fire(new Receiver<List<TipoDocumentoProxy>>() {

			@Override
			public void onSuccess(List<TipoDocumentoProxy> response) {
				lstTipoDocumento.setData(response);
				/*
				 * if (bean != null) { lstTipoDocumento.setSelectedItem(bean
				 * .getBeanTipoDocumento().getDescripcion()); }
				 */
				loadCorrelativo();
			}
		});
	}

	@Override
	public void loadCorrelativo() {
		// TODO Auto-generated method stub
		CntxMantCorrelativo context = FACTORY.cntxMantCorrelativo();
		FACTORY.initialize(EVENTBUS);
		Request<List<CorrelativoProxy>> request = context
				.listarCorrelativo(lstTipoDocumento.getSelectedItem()
						.getIdTipoDoc());
		request.fire(new Receiver<List<CorrelativoProxy>>() {

			@Override
			public void onSuccess(List<CorrelativoProxy> response) {
				lstCorrelativo.setData(response);
				/*
				 * if (bean != null) {
				 * lstSubFamilia.setSelectedItem(bean.getBeanSubFamilia()
				 * .getDescripcion()); }
				 */
			}
		});
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantUsuarioCorrelativo context = FACTORY
				.cntxMantUsuarioCorrelativo();
		FACTORY.initialize(EVENTBUS);
		UsuarioCorrelativoProxy bean = context
				.create(UsuarioCorrelativoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setIdUsuarioCorrelativo(lstTipoDocumento.getSelectedItem()
				.getIdTipoDoc());
		bean.setCodeCorrelativo(lstCorrelativo.getSelectedItem()
				.getIdCorrelativo());
		bean.setCodeUsuario(beanUsuario.getIdUsuario());
		Request<Boolean> request = context.insertarUsuarioCorrelativo(bean);
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
		CntxMantUsuarioCorrelativo context = FACTORY
				.cntxMantUsuarioCorrelativo();
		FACTORY.initialize(EVENTBUS);
		UsuarioCorrelativoProxy bean = context
				.create(UsuarioCorrelativoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		/*
		 * bean.setSerie(txtSerie.getText().toUpperCase());
		 * bean.setPreimpreso(txtPreimpreso.getText().toUpperCase());
		 * bean.setNumInicial(txtNumInicial.getText().toUpperCase());
		 * bean.setNumFinal(txtNumFinal.getText().toUpperCase());
		 */
		bean.setCodeUsuarioCorrelativo(this.bean.getIdUsuarioCorrelativo());
		/*
		 * bean.setBeanTipoDocumento(lstTipoDocumento.getSelectedItem());
		 * bean.setCodeTipoDoc
		 * (lstTipoDocumento.getSelectedItem().getIdTipoDoc());
		 */
		Request<Boolean> request = context.actualizarUsuarioCorrelativo(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
					not.showPopup();
					goToUiUsuarioCorrelativo();
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
		CntxMantUsuarioCorrelativo context = FACTORY
				.cntxMantUsuarioCorrelativo();
		FACTORY.initialize(EVENTBUS);
		UsuarioCorrelativoProxy bean = context
				.create(UsuarioCorrelativoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setCodeUsuarioCorrelativo(this.bean.getIdUsuarioCorrelativo());
		/*
		 * bean.setSerie(txtSerie.getText().toUpperCase());
		 * bean.setPreimpreso(txtPreimpreso.getText().toUpperCase());
		 * bean.setNumInicial(txtNumInicial.getText().toUpperCase());
		 * bean.setNumFinal(txtNumFinal.getText().toUpperCase());
		 */
		Request<Boolean> request = context.eliminarUsuarioCorrelativo(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
					not.showPopup();
					goToUiUsuarioCorrelativo();
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
		/*
		 * Date fecha = new Date(); CntxMantTrabajadorActiva context =
		 * FACTORY.cntxMantTrabajadorActiva(); FACTORY.initialize(EVENTBUS);
		 * TrabajadorActivaProxy bean = context
		 * .create(TrabajadorActivaProxy.class);
		 * bean.setVersion(fecha.getTime()); bean.setOperacion("I");
		 * bean.setCodeTrabajadorActiva(this.bean.getIdTrabajador());
		 * bean.setCodeTrabajador(this.bean.getIdTrabajador()); Request<Boolean>
		 * request = context.insertarTrabajadorActiva(bean); request.fire(new
		 * Receiver<Boolean>() {
		 * 
		 * @Override public void onSuccess(Boolean response) { // TODO
		 * Auto-generated method stub if (response) {
		 * Window.alert("Activado correctamente"); goToUiTrabajador(); } else {
		 * Window.alert("Error al activar"); } }
		 * 
		 * });
		 */
	}

	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub
		/*
		 * Date fecha = new Date(); CntxMantTrabajadorActiva context =
		 * FACTORY.cntxMantTrabajadorActiva(); FACTORY.initialize(EVENTBUS);
		 * TrabajadorActivaProxy bean = context
		 * .create(TrabajadorActivaProxy.class);
		 * bean.setVersion(fecha.getTime()); bean.setOperacion("A");
		 * bean.setCodeTrabajador(this.bean.getIdTrabajador());
		 * bean.setCodeTrabajadorActiva
		 * (this.bean.getCodeTrabajadorActivaActual()); Request<Boolean> request
		 * = context.actualizarTrabajadorActiva(bean); request.fire(new
		 * Receiver<Boolean>() {
		 * 
		 * @Override public void onSuccess(Boolean response) { if (response) {
		 * Window.alert("Desactivado correctamente"); goToUiTrabajador(); } else
		 * { Window.alert("Error al desactivar"); } } });
		 */
	}

	@Override
	public void goToBack() {
		uiHomeUsuarioCorrelativo.getContainer().showWidget(0);
		uiHomeUsuarioCorrelativo.getUiUsuarioCorrelativo().loadTable();
	}

	@Override
	public void goToUiUsuarioCorrelativo() {
		cleanForm();
		uiHomeUsuarioCorrelativo.getContainer().showWidget(0);
		uiHomeUsuarioCorrelativo.getUiUsuarioCorrelativo().loadTable();
	}
}
