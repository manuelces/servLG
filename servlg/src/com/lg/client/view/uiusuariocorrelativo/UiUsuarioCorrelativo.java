package com.lg.client.view.uiusuariocorrelativo;

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
import com.lg.client.view.grid.GridUsuarioCorrelativo;

public class UiUsuarioCorrelativo extends UiFormMantenimiento implements
		KeyUpHandler, InterUiUsuarioCorrelativo {
	// private Label lblBuscar;
	private MSearchBox txtBuscar;
	// private FlexTable formBuscar;
	protected GridUsuarioCorrelativo grid;
	private Button btnActivar;
	private Button btnDesactivar;

	public UiUsuarioCorrelativo() {
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
		grid = new GridUsuarioCorrelativo();
		// grid.setMinimumTableWidth(1024, Style.Unit.PX);
		this.getPnlTabla().add(grid);
		this.getPnlTabla().add(grid.getPager());
		// this.getPnlBusqueda().add(formBuscar);
		this.getPnlBusqueda().add(txtBuscar);
		btnActivar = new Button("Activar");
		btnDesactivar = new Button("Desactivar");
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

	ClickHandler clickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			if (event.getSource().equals(btnActivar)) {
				activarUsuarioCorrelativo();
			} else if (event.getSource().equals(btnDesactivar)) {
				desactivarUsuarioCorrelativo();
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
	public void desactivarUsuarioCorrelativo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activarUsuarioCorrelativo() {
		// TODO Auto-generated method stub

	}
}
