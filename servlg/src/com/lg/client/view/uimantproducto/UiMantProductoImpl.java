package com.lg.client.view.uimantproducto;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.FamiliaProxy;
import com.lg.client.beanproxy.MarcaProxy;
import com.lg.client.beanproxy.ProductoProxy;
import com.lg.client.beanproxy.SubFamiliaProxy;
import com.lg.client.requestfactory.CntxMantFamilia;
import com.lg.client.requestfactory.CntxMantMarca;
import com.lg.client.requestfactory.CntxMantProducto;
import com.lg.client.requestfactory.CntxMantSubFamilia;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uiproducto.UiHomeProducto;

public class UiMantProductoImpl extends UiMantProducto {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UiHomeProducto uiHomeProducto;

	public UiMantProductoImpl(UiHomeProducto uiHomeProducto) {
		this.uiHomeProducto = uiHomeProducto;
		loadListBoxFamilia();
		loadListBoxMarca();
	}

	@Override
	public void loadListBoxFamilia() {
		// TODO Auto-generated method stub
		CntxMantFamilia context = FACTORY.cntxMantFamilia();
		FACTORY.initialize(EVENTBUS);
		Request<List<FamiliaProxy>> request = context.listarFamilia();
		request.fire(new Receiver<List<FamiliaProxy>>() {

			@Override
			public void onSuccess(List<FamiliaProxy> response) {
				lstFamilia.setData(response);
				if (bean != null) {
					lstFamilia.setSelectedItem(bean.getDescFamilia());
				}
				loadListBoxSubFamilia();
			}
		});
	}

	@Override
	public void loadListBoxSubFamilia() {
		// TODO Auto-generated method stub		
		CntxMantSubFamilia context = FACTORY.cntxMantSubFamilia();
		FACTORY.initialize(EVENTBUS);
		Request<List<SubFamiliaProxy>> request = context
				.listarSubFamilia(lstFamilia.getSelectedItem().getIdFamilia());
		request.fire(new Receiver<List<SubFamiliaProxy>>() {

			@Override
			public void onSuccess(List<SubFamiliaProxy> response) {
				lstSubFamilia.setData(response);
				if (bean != null) {
					lstSubFamilia.setSelectedItem(bean.getBeanSubFamilia().getDescripcion());
				}
			}
		});
	}

	@Override
	public void loadListBoxMarca() {
		// TODO Auto-generated method stub
		CntxMantMarca context = FACTORY.cntxMantMarca();
		FACTORY.initialize(EVENTBUS);
		Request<List<MarcaProxy>> request = context.listarMarca();
		request.fire(new Receiver<List<MarcaProxy>>() {

			@Override
			public void onSuccess(List<MarcaProxy> response) {
				lstMarca.setData(response);
				if (bean != null) {
					lstMarca.setSelectedItem(bean.getBeanMarca().getDescripcion());
				}
			}
		});
	}

	@Override
	public void processInsertar() {
		Date fecha = new Date();
		CntxMantProducto context = FACTORY.cntxMantProducto();
		FACTORY.initialize(EVENTBUS);
		ProductoProxy bean = context.create(ProductoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("I");
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setModelo(txtModelo.getText().toUpperCase());
		bean.setCodeFamilia(lstFamilia.getSelectedItem().getIdFamilia());
		bean.setDescFamilia(lstFamilia.getSelectedItem().getDescripcion());
		bean.setIdProducto(lstSubFamilia.getSelectedItem().getIdSubFamilia());
		bean.setBeanMarca(lstMarca.getSelectedItem());
		//bean.setBeanSubFamilia(lstSubFamilia.getSelectedItem());
		bean.setCodeMarca(lstMarca.getSelectedItem().getIdMarca());
		//bean.setCodeSubFamilia(lstSubFamilia.getSelectedItem().getIdSubFamilia());
		Request<Boolean> request = context.insertarProducto(bean);
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
		CntxMantProducto context = FACTORY.cntxMantProducto();
		FACTORY.initialize(EVENTBUS);
		ProductoProxy bean = context.create(ProductoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("A");
		bean.setDescripcion(txtDescripcion.getText().toUpperCase());
		bean.setModelo(txtModelo.getText().toUpperCase());
		bean.setCodeFamilia(lstFamilia.getSelectedItem().getIdFamilia());
		bean.setDescFamilia(lstFamilia.getSelectedItem().getDescripcion());		
		bean.setBeanMarca(lstMarca.getSelectedItem());
		bean.setCodeMarca(lstMarca.getSelectedItem().getIdMarca());
		bean.setBeanSubFamilia(lstSubFamilia.getSelectedItem());
		bean.setCodeSubFamilia(lstSubFamilia.getSelectedItem().getCodeSubFamilia());
		bean.setCodeProducto(this.bean.getIdProducto());
		Request<Boolean> request = context.actualizarProducto(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Actualizado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
					goToUiProducto();
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
		CntxMantProducto context = FACTORY.cntxMantProducto();
		FACTORY.initialize(EVENTBUS);
		ProductoProxy bean = context.create(ProductoProxy.class);
		bean.setVersion(fecha.getTime());
		bean.setOperacion("E");
		bean.setCodeProducto(this.bean.getIdProducto());
		Request<Boolean> request = context.eliminarProducto(bean);
		request.fire(new Receiver<Boolean>() {

			@Override
			public void onSuccess(Boolean response) {
				if (response) {
					//Window.alert("Eliminado correctamente");
					Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
					goToUiProducto();
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
		uiHomeProducto.getContainer().showWidget(0);
		uiHomeProducto.getUiProducto().loadTable();
	}

	@Override
	public void goToUiProducto() {
		cleanForm();
		uiHomeProducto.getContainer().showWidget(0);
		uiHomeProducto.getUiProducto().loadTable();
	}

}
