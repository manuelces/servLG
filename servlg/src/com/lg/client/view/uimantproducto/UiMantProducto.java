package com.lg.client.view.uimantproducto;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.ProductoProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.view.listboxmodel.ListModelFamilia;
import com.lg.client.view.listboxmodel.ListModelMarca;
import com.lg.client.view.listboxmodel.ListModelSubFamilia;

public class UiMantProducto extends UiMantenimiento implements
		InterUiMantProducto, KeyUpHandler, ChangeHandler, BlurHandler {
	protected MTextBox txtId;
	protected MTextBox txtDescripcion;
	protected MTextBox txtModelo;
	protected ListModelFamilia lstFamilia;
	protected ListModelSubFamilia lstSubFamilia;
	protected ListModelMarca lstMarca;
	protected ProductoProxy bean;

	public UiMantProducto() {
		initComponents();
		initListener();
		initStyle();
	}

	private void initComponents() {
		txtId = new MTextBox();
		txtDescripcion = new MTextBox();
		txtModelo = new MTextBox();
		lstFamilia = new ListModelFamilia();
		lstSubFamilia = new ListModelSubFamilia();
		lstMarca = new ListModelMarca();
		//this.addWidget("ID", txtId);
		this.addWidget("FAMILIA", lstFamilia);
		this.addWidget("SUBFAMILIA", lstSubFamilia);
		this.addWidget("MARCA", lstMarca);
		this.addWidget("DESCRIPCION", txtDescripcion);
		this.addWidget("# PARTE", txtModelo);
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
		lstFamilia.addChangeHandler(this);
		lstFamilia.addBlurHandler(this);
	}

	private void initStyle() {
		lstFamilia.setWidth("100%");
		lstSubFamilia.setWidth("100%");
		lstMarca.setWidth("100%");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			txtId.setText(this.bean.getIdProducto().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(true);
			txtModelo.setText(this.bean.getModelo());
			txtModelo.setEnabled(true);
			lstFamilia.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
			txtId.setText(this.bean.getIdProducto().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(false);
			txtModelo.setText(this.bean.getModelo());
			txtModelo.setEnabled(false);
			lstFamilia.setEnabled(false);
			lstSubFamilia.setEnabled(false);
			lstMarca.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			txtId.setText(this.bean.getIdProducto().toString());
			txtDescripcion.setText(this.bean.getDescripcion());
			txtId.setEnabled(false);
			txtDescripcion.setEnabled(false);
			txtModelo.setText(this.bean.getModelo());
			txtModelo.setEnabled(false);			
			lstFamilia.setEnabled(false);
			lstSubFamilia.setEnabled(false);
			lstMarca.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {
			txtId.setEnabled(false);
			txtDescripcion.setFocus(true);
			txtDescripcion.setEnabled(true);
			txtModelo.setEnabled(true);
			lstFamilia.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
		txtDescripcion.setText(null);
		txtModelo.setText(null);
	}

	@Override
	public void goToUiProducto() {
		// TODO Auto-generated method stub

	}

	public void setBean(ProductoProxy bean) {
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
					Window.alert("Operaci�n no contemplada");
				}
			}
		}
	}

	@Override
	public void loadListBoxFamilia() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadListBoxSubFamilia() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadListBoxMarca() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChange(ChangeEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(lstFamilia)) {
			loadListBoxSubFamilia();
		}
	}

	@Override
	public void onBlur(BlurEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(lstFamilia)) {
			loadListBoxSubFamilia();
		}
	}
	
	@Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnOperacion)) {
            if (modo.equalsIgnoreCase(MODOINSERTAR)) {                
                processInsertar();
            } else if (modo.equalsIgnoreCase(MODOUPDATE)) {                
                processActualizar();
            } else if (modo.equalsIgnoreCase(MODODELETE)) {
                processEliminar();
            } else {
                Window.alert("Operacion no contemplada");
            }
        }
    }
}
