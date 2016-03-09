package com.lg.client.view.uimantmarca;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.MarcaProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;

public class UiMantMarca extends UiMantenimiento implements InterUiMantMarca,
		KeyUpHandler {
	protected MTextBox txtId;
	protected MTextBox txtAbreviatura;
	protected MTextBox txtDescripcion;
	protected MarcaProxy bean;

	public UiMantMarca() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtId = new MTextBox();
		txtAbreviatura = new MTextBox();
		txtDescripcion = new MTextBox();
		// this.addWidget("ID", txtId);
		this.addWidget("ABREVIATURA (*)", txtAbreviatura);
		this.addWidget("DESCRIPCION (*)", txtDescripcion);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}
		});
	}

	private void reCalcularWindows() {
		int alto = Window.getClientHeight() - 220;
		this.scrollPanel.setHeight(alto + "px");
	}

	private void initListener() {
		txtDescripcion.addKeyUpHandler(this);
	}

	private void initStyle() {

	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			txtId.setText(this.bean.getIdMarca().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(true);
			txtDescripcion.setEnabled(true);
			txtDescripcion.setFocus(true);
			txtDescripcion.selectAll();
			txtAbreviatura.setText(this.bean.getAbreviatura());
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
			txtId.setText(this.bean.getIdMarca().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(false);
			txtAbreviatura.setText(this.bean.getAbreviatura());
			txtAbreviatura.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			txtId.setText(this.bean.getIdMarca().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(false);
			txtAbreviatura.setText(this.bean.getAbreviatura());
			txtAbreviatura.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {
			txtId.setEnabled(false);
			txtAbreviatura.setFocus(true);
			txtDescripcion.setEnabled(true);
			txtDescripcion.selectAll();
			txtAbreviatura.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
		txtDescripcion.setText(null);
		txtAbreviatura.setText(null);
	}

	@Override
	public void goToUiMarca() {
		// TODO Auto-generated method stub

	}

	public void setBean(MarcaProxy bean) {
		this.bean = bean;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(txtDescripcion)) {
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
				if (modo.equalsIgnoreCase(MODOINSERTAR)) {
					processInsertar();
				} else if (modo.equalsIgnoreCase(MODOUPDATE)) {
					processActualizar();
				} else {
					Window.alert("Operacion no contemplada");
				}
			}
		}
	}
}
