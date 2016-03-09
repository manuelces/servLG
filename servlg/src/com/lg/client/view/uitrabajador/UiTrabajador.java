package com.lg.client.view.uitrabajador;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.lg.client.resource.MyResource;
import com.lg.client.util.UiFormMantenimiento;
import com.lg.client.view.grid.GridTrabajador;
import com.lg.client.view.uitrabajador.InterUiTrabajador;

public class UiTrabajador extends UiFormMantenimiento implements KeyUpHandler,
		InterUiTrabajador {
	// private Label lblBuscar;
	private MSearchBox txtBuscar;
	// private FlexTable formBuscar;
	protected GridTrabajador grid;
	private Button btnActivar;
	private Button btnDesactivar;

	public UiTrabajador() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		// formBuscar = new FlexTable();
		// lblBuscar = new Label("Buscar:");
		txtBuscar = new MSearchBox();
		// txtBuscar.getElement().setPropertyString("placeholder",
		// "escriba aqui");
		txtBuscar.setPlaceHolder("Buscar");
		// formBuscar.setWidget(0, 0, lblBuscar);
		// formBuscar.setWidget(0, 1, txtBuscar);
		grid = new GridTrabajador();
		// grid.setMinimumTableWidth(1024, Style.Unit.PX);
		this.getPnlTabla().add(grid);
		this.getPnlTabla().add(grid.getPager());
		// this.getPnlBusqueda().add(formBuscar);
		this.getPnlBusqueda().add(txtBuscar);
		btnActivar=new Button("Activar");
		btnDesactivar=new Button("Desactivar");
		this.getPnlBotones().add(btnActivar);
		this.getPnlBotones().add(btnDesactivar);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}

		});
	}

	private void initListener() {
		txtBuscar.textBox.addKeyUpHandler(this);
		btnActivar.addClickHandler(clickHandler);
		btnDesactivar.addClickHandler(clickHandler);
	}
	
	ClickHandler clickHandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource().equals(btnActivar)){
				activarTrabajador();
			}else if(event.getSource().equals(btnDesactivar)){
				desactivarTrabajador();
			}
		}
		
	};

	private void initStyle() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		txtBuscar.getElement().getFirstChild().getFirstChild()
				.removeFromParent();
	}

	private void reCalcularWindows() {
		int alto = Window.getClientHeight() - 150;
		this.getPnlTabla().setHeight(alto + "px");
		this.getPnlBotones().setHeight(alto + "px");
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		grid.getDataProvider().setFilter(txtBuscar.getText());
		grid.getDataProvider().refresh();
	}

	@Override
	public void desactivarTrabajador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activarTrabajador() {
		// TODO Auto-generated method stub
		
	}
}
