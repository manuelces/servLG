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
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.client.requestfactory.CntxMantUsuario;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.resource.MyResource;
import com.lg.client.view.grid.GridUsuario;

public class UIInputUsuario extends HorizontalPanel implements KeyUpHandler {
	public TextBox txtInputUsuario;
	public PushButton btnCombo;
	public Image imgCombo;
	public GridUsuario gridUsuario;
	private MSearchBox txtBuscar;
	private PopupPanel simplePopup;
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();

	public UIInputUsuario() {
		initComponents();
		initListener();
		initStyle();
		initTable();
	}

	private void initTable() {
		CntxMantUsuario context = FACTORY.cntxMantUsuario();
		FACTORY.initialize(EVENTBUS);
		Request<List<UsuarioProxy>> request = context.listarUsuario().with(
				"beanTrabajador");// ,"listActiva"
		request.fire(new Receiver<List<UsuarioProxy>>() {

			@Override
			public void onSuccess(List<UsuarioProxy> response) {
				gridUsuario.getSelectionModel().clear();
				gridUsuario.setData(response);
				// lstBdEmpresa.setData(grid.getData());
			}
		});
	}

	private void initComponents() {
		gridUsuario = new GridUsuario();
		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder("Buscar");
		txtInputUsuario = new TextBox();
		imgCombo = new Image(MyResource.INSTANCE.getImgAbajo32());
		btnCombo = new PushButton(imgCombo);
		txtInputUsuario.setWidth("100%");
		simplePopup = new PopupPanel(true);
		gridUsuario.setHeight("300px");
		gridUsuario.setWidth("600px");
		simplePopup.setWidth("600px");
		simplePopup.setHeight("300px");
		FlowPanel flow = new FlowPanel();
		FlowPanel flowGrid = new FlowPanel();
		FlowPanel flowBusqueda = new FlowPanel();
		flowBusqueda.add(txtBuscar);
		flowGrid.add(gridUsuario);
		flow.add(flowBusqueda);
		flow.add(flowGrid);
		simplePopup.setWidget(flow);
		this.add(txtInputUsuario);
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
		txtInputUsuario.setWidth("100%");
	}

	public void showPopup() {
		int left = txtInputUsuario.getAbsoluteLeft();
		int top = txtInputUsuario.getAbsoluteTop() + 24;
		simplePopup.setPopupPosition(left, top);
		simplePopup.show();
		// gridPrecioItem.getSelectionModel().clear();
		gridUsuario.setFocus(true);
	}

	public void hidePopup() {
		simplePopup.hide();
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		gridUsuario.getDataProvider().setFilter(txtBuscar.getText());
		gridUsuario.getDataProvider().refresh();
	}

}
