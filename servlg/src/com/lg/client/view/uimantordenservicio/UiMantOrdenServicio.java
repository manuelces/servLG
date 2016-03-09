package com.lg.client.view.uimantordenservicio;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.lg.client.model.HeaderMenu;
import com.lg.client.model.MButton;
import com.lg.client.resource.MyResource;
import com.lg.client.uiutil.UiSeparador;
import com.lg.client.view.listboxmodel.ListModelElectro;
import com.lg.client.view.listboxmodel.ListModelEmpresaFabricante;
import com.lg.client.view.listboxmodel.ListModelTipoDocumento;
import com.lg.client.view.listboxmodel.ListModelTipoServicio;
import com.lg.shared.FieldVerifier;
import com.sun.java.swing.plaf.windows.resources.windows;

public class UiMantOrdenServicio extends Composite implements
		InterUiMantOrdenServicio, ClickHandler, ChangeHandler, KeyUpHandler,
		FocusHandler {

	private FlowPanel pnlContenedor;
	private HeaderMenu header;
	private Label lblCenter;
	private PushButton btnBack;
	public ScrollPanel scrollPanel;
	private VerticalPanel contenido;
	private MButton btnOperacion;
	// private FlexTable pnlCenter;
	private RadioButton rbGarantia;
	private RadioButton rbSinGarantia;
	private TextBox txtDocCliente;
	private TextBox txtCliente;
	private TextBox txtNumSerie;
	private TextBox txtSerie;
	private TextBox txtPreimpreso;
	private TextBox txtTienda;
	protected ListModelEmpresaFabricante lstEmpresaFabricante;
	protected ListModelTipoServicio lstTipoServicio;
	private Button btnCliente;
	protected ListModelElectro lstElectro;
	protected ListModelTipoDocumento lstTipoDocumento;
	private DateBox fechaCompra;
	private DateBox fechaIngreso;
	private DateBox fechaCompromiso;
	private TextArea txtSintoma;

	public UiMantOrdenServicio() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		header = new HeaderMenu();
		lblCenter = new Label("Orden de Servicio");
		header.setCenterWidget(lblCenter);
		btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
		btnBack.setTitle("Volver Atras");
		header.setLeftWidget(btnBack);
		contenido = new VerticalPanel();
		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		scrollPanel.setWidget(contenido);
		btnOperacion = new MButton("ACEPTAR");
		// btnOperacion.setConfirm(true);
		initContenido();
		contenido.add(btnOperacion);
		pnlContenedor = new FlowPanel();
		pnlContenedor.add(header);
		pnlContenedor.add(scrollPanel);
		this.initWidget(pnlContenedor);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}
		});
	}

	private void initContenido() {
		HorizontalPanel pnlGarantia = new HorizontalPanel();
		rbGarantia = new RadioButton("garantia", "Con garantia");
		rbGarantia.setValue(true);
		rbSinGarantia = new RadioButton("garantia", "Sin garantia");
		pnlGarantia.add(rbGarantia);
		pnlGarantia.add(new UiSeparador().isSpace());
		pnlGarantia.add(rbSinGarantia);
		contenido.add(pnlGarantia);
		HorizontalPanel pnlServicio = new HorizontalPanel();
		Label lblEmpr = new Label("Concesionario");
		lblEmpr.setWidth("80px");
		pnlServicio.add(lblEmpr);
		pnlServicio.add(new UiSeparador().isSpace());
		lstEmpresaFabricante = new ListModelEmpresaFabricante();
		pnlServicio.add(lstEmpresaFabricante);
		pnlServicio.add(new Label("Servicio"));
		lstTipoServicio = new ListModelTipoServicio();
		pnlServicio.add(lstTipoServicio);
		contenido.add(pnlServicio);
		HorizontalPanel pnlCliente = new HorizontalPanel();
		Label lblCliente = new Label("Cliente");
		lblCliente.setWidth("80px");
		pnlCliente.add(lblCliente);
		pnlCliente.add(new UiSeparador().isSpace());
		txtDocCliente = new TextBox();
		txtCliente = new TextBox();
		txtCliente.setEnabled(false);
		pnlCliente.add(txtDocCliente);
		pnlCliente.add(txtCliente);
		btnCliente = new Button("Buscar");
		pnlCliente.add(new UiSeparador().isSpace());
		pnlCliente.add(btnCliente);
		contenido.add(pnlCliente);
		HorizontalPanel pnlModelo = new HorizontalPanel();
		Label lblModelo = new Label("Modelo");
		lblModelo.setWidth("80px");
		pnlModelo.add(lblModelo);
		lstElectro = new ListModelElectro();
		pnlModelo.add(new UiSeparador().isSpace());
		pnlModelo.add(lstElectro);
		pnlModelo.add(new UiSeparador().isSpace());
		pnlModelo.add(new Label("N° Serie"));
		pnlModelo.add(new UiSeparador().isSpace());
		txtNumSerie = new TextBox();
		pnlModelo.add(txtNumSerie);
		contenido.add(pnlModelo);
		HorizontalPanel pnlDocCompra = new HorizontalPanel();
		Label lblTipoDoc = new Label("Doc Compra");
		lblTipoDoc.setWidth("80px");
		pnlDocCompra.add(lblTipoDoc);
		pnlDocCompra.add(new UiSeparador().isSpace());
		lstTipoDocumento = new ListModelTipoDocumento();
		pnlDocCompra.add(lstTipoDocumento);
		pnlDocCompra.add(new UiSeparador().isSpace());
		Label lblNumDoc = new Label("N°");
		// lblNumDoc.setWidth("80px");
		pnlDocCompra.add(lblNumDoc);
		pnlDocCompra.add(new UiSeparador().isSpace());
		txtSerie = new TextBox();
		pnlDocCompra.add(txtSerie);
		txtPreimpreso = new TextBox();
		pnlDocCompra.add(txtPreimpreso);
		pnlDocCompra.add(new UiSeparador().isSpace());
		Label lblfechaCompra = new Label("Fecha Compra:");
		pnlDocCompra.add(lblfechaCompra);
		pnlDocCompra.add(new UiSeparador().isSpace());
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		fechaCompra = new DateBox();
		fechaCompra.setFormat(new DateBox.DefaultFormat(dateFormat));
		fechaCompra.getDatePicker().setYearArrowsVisible(true);
		pnlDocCompra.add(fechaCompra);
		contenido.add(pnlDocCompra);
		HorizontalPanel pnlTienda = new HorizontalPanel();
		Label lblTienda = new Label("Tienda");
		lblTienda.setWidth("80px");
		pnlTienda.add(lblTienda);
		pnlTienda.add(new UiSeparador().isSpace());
		txtTienda = new TextBox();
		pnlTienda.add(txtTienda);
		contenido.add(pnlTienda);
		HorizontalPanel pnlFecha = new HorizontalPanel();
		Label lblFechaIngreso = new Label("Fecha Ing:");
		lblFechaIngreso.setWidth("80px");
		pnlFecha.add(lblFechaIngreso);
		pnlFecha.add(new UiSeparador().isSpace());
		fechaIngreso = new DateBox();
		fechaIngreso.setFormat(new DateBox.DefaultFormat(dateFormat));
		fechaIngreso.getDatePicker().setYearArrowsVisible(true);
		pnlFecha.add(fechaIngreso);
		pnlFecha.add(new UiSeparador().isSpace());
		Label lblFechaCompromiso = new Label("Fecha Compromiso:");
		// lblFechaIngreso.setWidth("80px");
		pnlFecha.add(lblFechaCompromiso);
		pnlFecha.add(new UiSeparador().isSpace());
		fechaCompromiso = new DateBox();
		fechaCompromiso.setFormat(new DateBox.DefaultFormat(dateFormat));
		fechaCompromiso.getDatePicker().setYearArrowsVisible(true);
		pnlFecha.add(fechaCompromiso);
		contenido.add(pnlFecha);
		HorizontalPanel pnlSintoma = new HorizontalPanel();
		Label lblSintoma = new Label("Sintomas");
		lblSintoma.setWidth("80px");
		pnlSintoma.add(lblSintoma);
		pnlSintoma.add(new UiSeparador().isSpace());
		txtSintoma = new TextArea();
		txtSintoma.setVisibleLines(5);
		pnlSintoma.add(txtSintoma);
		contenido.add(pnlSintoma);
	}

	private void initListener() {
		// btnOperacion.addTouchEndHandler(this);
		btnBack.addClickHandler(this);
		btnCliente.addClickHandler(this);
		lstEmpresaFabricante.addChangeHandler(this);
		txtDocCliente.addKeyUpHandler(this);
		txtDocCliente.addFocusHandler(this);
		txtSerie.addKeyUpHandler(this);
		txtSerie.addFocusHandler(this);
		txtPreimpreso.addKeyUpHandler(this);
		txtPreimpreso.addFocusHandler(this);
	}

	private void initStyle() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
		contenido.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		btnOperacion.getElement().getStyle().setMargin(0, Style.Unit.PX);
		// pnlForm.getElement().getStyle().setPadding(10, Style.Unit.PX);
		contenido.getElement().getStyle().setDisplay(Style.Display.BLOCK);
		txtDocCliente.setMaxLength(11);
		txtSerie.setMaxLength(4);
		txtPreimpreso.setMaxLength(10);

	}

	private void reCalcularWindows() {
		int ancho = Window.getClientWidth() - 30;
		btnOperacion.setWidth(ancho + "px");
		txtDocCliente.setWidth("100px");
		txtSerie.setWidth("50px");
		txtPreimpreso.setWidth("100px");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goToUiOrdenServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(btnBack)) {
			// goToBack();
		}
		if (event.getSource().equals(btnCliente)) {
			goUISearchAddCliente();
		}
	}
	

	@Override
	public void loadEmpresaFabricante() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChange(ChangeEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(lstEmpresaFabricante)) {
			loadTipoServicio();
		}
	}

	@Override
	public void loadTipoServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFocus(FocusEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(txtDocCliente)) {
			txtDocCliente.selectAll();
		}
		if (event.getSource().equals(txtSerie)) {
			txtSerie.selectAll();
		}
		if (event.getSource().equals(txtPreimpreso)) {
			txtPreimpreso.selectAll();
		}
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		if (event.getSource().equals(txtDocCliente)) {
			if (!FieldVerifier.isCadenaNumberEntero(txtDocCliente.getText()
					.trim())) {
				txtDocCliente.setText("");
			}
		}
		if (event.getSource().equals(txtSerie)) {
			if (!FieldVerifier.isCadenaNumberEntero(txtSerie.getText().trim())) {
				txtSerie.setText("");
			}
		}
		if (event.getSource().equals(txtPreimpreso)) {
			if (!FieldVerifier.isCadenaNumberEntero(txtPreimpreso.getText()
					.trim())) {
				txtPreimpreso.setText("");
			}
		}
	}

	@Override
	public void loadElectro() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadTipoDocumento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goUISearchAddCliente() {
		// TODO Auto-generated method stub
		
	}

}
