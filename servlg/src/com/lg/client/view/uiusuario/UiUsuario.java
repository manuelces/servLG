package com.lg.client.view.uiusuario;

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
import com.lg.client.view.grid.GridUsuario;
import com.lg.client.view.uiusuario.InterUiUsuario;

public class UiUsuario extends UiFormMantenimiento implements KeyUpHandler,
		InterUiUsuario {
	private MSearchBox txtBuscar;
	protected GridUsuario grid;
	private Button btnActivar;
	private Button btnDesactivar;

	public UiUsuario() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder("Buscar");
		grid = new GridUsuario();
		this.getPnlTabla().add(grid);
		this.getPnlTabla().add(grid.getPager());
		// this.getPnlBusqueda().add(formBuscar);
		this.getPnlBusqueda().add(txtBuscar);
		btnActivar=new Button("Activar");
		btnDesactivar=new Button("Desactivar");
		this.setVisible(2, false);
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
				activarUsuario();
			}else if(event.getSource().equals(btnDesactivar)){
				desactivarUsuario();
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
	public void desactivarUsuario() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activarUsuario() {
		// TODO Auto-generated method stub
		
	}
}
