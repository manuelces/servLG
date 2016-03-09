package com.lg.client.uiutil;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.requestfactory.CntxMantTrabajador;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.resource.MyResource;
import com.lg.client.view.grid.GridTrabajador;

public class UIInputTrabajador extends HorizontalPanel implements KeyUpHandler {
	public MTextBox txtInputTrabajador;
	public PushButton btnCombo;
	public Image imgCombo;
	public GridTrabajador gridTrabajador;
	private MSearchBox txtBuscar;
	private PopupPanel simplePopup;
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();

	public UIInputTrabajador() {
		initComponents();
		initListener();
		initStyle();
		initTable();
	}

	private void initTable() {
		CntxMantTrabajador context = FACTORY.cntxMantTrabajador();
		FACTORY.initialize(EVENTBUS);
		Request<List<TrabajadorProxy>> request = context.listarTrabajador()
				.with("beanTipoTrabajador");//,"listActiva"
		request.fire(new Receiver<List<TrabajadorProxy>>() {

			@Override
			public void onSuccess(List<TrabajadorProxy> response) {
				gridTrabajador.getSelectionModel().clear();
				gridTrabajador.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	private void initComponents() {
		gridTrabajador = new GridTrabajador();
		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder("Buscar");
		txtInputTrabajador = new MTextBox();
		imgCombo = new Image(MyResource.INSTANCE.getImgAbajo32());
		btnCombo = new PushButton(imgCombo);
		txtInputTrabajador.setWidth("100%");
		simplePopup = new PopupPanel(true);
		gridTrabajador.setHeight("300px");
		gridTrabajador.setWidth("600px");
		simplePopup.setWidth("600px");
		simplePopup.setHeight("300px");
		FlowPanel flow = new FlowPanel();
		FlowPanel flowGrid = new FlowPanel();
		FlowPanel flowBusqueda = new FlowPanel();
		flowBusqueda.add(txtBuscar);
		flowGrid.add(gridTrabajador);
		flow.add(flowBusqueda);
		flow.add(flowGrid);
		simplePopup.setWidget(flow);
		this.add(txtInputTrabajador);
		this.add(btnCombo);
	}

	private void initListener() {
		// btnCombo.addClickHandler(this);
		// txtInputPrecio.addKeyUpHandler(this);
		txtBuscar.textBox.addKeyUpHandler(this);
	}

	private void initStyle() {
		imgCombo.setWidth("16px");
		imgCombo.setHeight("16px");
		btnCombo.setWidth("16px");
		btnCombo.setHeight("16px");
		txtBuscar.getElement().getFirstChild().getFirstChild()
				.removeFromParent();
		txtInputTrabajador.setWidth("100%");
	}

	public void showPopup() {
		int left = txtInputTrabajador.getAbsoluteLeft();
		int top = txtInputTrabajador.getAbsoluteTop() + 24;
		simplePopup.setPopupPosition(left, top);
		simplePopup.show();
		// gridPrecioItem.getSelectionModel().clear();
		gridTrabajador.setFocus(true);
	}

	public void hidePopup() {
		simplePopup.hide();
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		gridTrabajador.getDataProvider().setFilter(txtBuscar.getText());
		gridTrabajador.getDataProvider().refresh();
	}

}
