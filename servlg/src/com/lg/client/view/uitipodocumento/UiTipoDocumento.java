package com.lg.client.view.uitipodocumento;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.lg.client.resource.MyResource;
import com.lg.client.util.UiFormMantenimiento;
import com.lg.client.view.grid.GridTipoDocumento;

public class UiTipoDocumento extends UiFormMantenimiento implements
		KeyUpHandler, InterUiTipoDocumento {

	private MSearchBox txtBuscar;
	protected GridTipoDocumento grid;

	public UiTipoDocumento() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder("Buscar");
		grid = new GridTipoDocumento();
		this.getPnlTabla().add(grid);
		this.getPnlTabla().add(grid.getPager());
		this.getPnlBusqueda().add(txtBuscar);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}

		});
	}

	private void initListener() {
		txtBuscar.textBox.addKeyUpHandler(this);
	}

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
}
