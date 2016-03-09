/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lg.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressBar;
import com.lg.client.resource.MyResource;
import com.lg.client.util.UiFormMantenimiento;
import com.lg.client.view.grid.GridCliente;

/**
 *
 * @author SISTEMAS
 */
public class UIBuscarCliente extends UiFormMantenimiento implements
		KeyUpHandler, TouchEndHandler {

	protected ProgressBar progreso;
	protected MSearchBox txtBuscar;
	public GridCliente grid;
	private FlowPanel pnlGuardar;
	private Button btnGuardar;

	public UIBuscarCliente() {
		initComponents();
		initStyle();
		initListener();
		reCalcularWindows();
	}

	private void initComponents() {
		progreso = new ProgressBar();
		txtBuscar = new MSearchBox();
		txtBuscar.getElement().setPropertyString("placeholder", "escriba aqui");
		grid = new GridCliente();
		grid.setMinimumTableWidth(1024, Style.Unit.PX);
		pnlGuardar = new FlowPanel();
		btnGuardar = new Button("Seleccionar");
		pnlGuardar.add(btnGuardar);
		btnGuardar.setConfirm(true);
		this.getPnlTabla().add(grid);
		this.getPnlTabla().add(grid.getPager());
		this.getPnlTabla().add(pnlGuardar);
		this.getPnlBusqueda().add(txtBuscar);
		this.getPnlBotones().setVisible(false);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}
		});
	}

	private void initListener() {
		txtBuscar.textBox.addKeyUpHandler(this);
		btnGuardar.addTouchEndHandler(this);
	}

	ClickHandler clickHandler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
		}

	};

	private void initStyle() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		pnlGuardar.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
		btnGuardar.setWidth("90%");
		this.getPnlUnion().setWidth("100%");
		MyResource.INSTANCE.getStlModel().ensureInjected();
		txtBuscar.getElement().getFirstChild().getFirstChild()
				.removeFromParent();
	}

	private void reCalcularWindows() {
		int alto = Window.getClientHeight();
		this.getPnlTabla().setHeight(alto - 340 + "px");
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		grid.getDataProvider().setFilter(txtBuscar.getText());
		grid.getDataProvider().refresh();
	}

	@Override
	public void loadTable() {

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		
	}

}
