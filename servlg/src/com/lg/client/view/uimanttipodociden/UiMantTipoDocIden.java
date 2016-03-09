package com.lg.client.view.uimanttipodociden;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.TipoDocIdenProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;

public class UiMantTipoDocIden extends UiMantenimiento implements
		InterUiMantTipoDocIden, KeyUpHandler {

	//protected MMTextBox txtId;
	protected MTextBox txtIdTipoDoc;
	protected MTextBox txtAbreviatura;
	protected MTextBox txtDescripcion;
	protected TipoDocIdenProxy bean;

	public UiMantTipoDocIden() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		//txtId = new MMTextBox();
		txtAbreviatura = new MTextBox();
		txtDescripcion = new MTextBox();
		txtIdTipoDoc = new MTextBox();
		// this.addWidget("ID", txtId);
		this.addWidget("ID (*)", txtIdTipoDoc);
		this.addWidget("DESCRIPCION (*)", txtDescripcion);
		this.addWidget("ABREVIATURA (*)", txtAbreviatura);
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
			txtDescripcion.setText(this.bean.getDescripcion());			
			txtDescripcion.setEnabled(true);
			txtDescripcion.setFocus(true);
			txtDescripcion.selectAll();
			txtAbreviatura.setText(this.bean.getAbrev());
			txtIdTipoDoc.setText(this.bean.getIdTipoDoc());
			txtIdTipoDoc.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)) {			
			txtDescripcion.setText(this.bean.getDescripcion());
			txtDescripcion.setEnabled(false);
			txtAbreviatura.setText(this.bean.getAbrev());
			txtIdTipoDoc.setText(this.bean.getIdTipoDoc());
			txtAbreviatura.setEnabled(false);
			txtIdTipoDoc.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {			
			txtDescripcion.setText(this.bean.getDescripcion());			
			txtDescripcion.setEnabled(false);
			txtAbreviatura.setText(this.bean.getAbrev());
			txtAbreviatura.setEnabled(false);
			txtIdTipoDoc.setText(this.bean.getIdTipoDoc());
			txtIdTipoDoc.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {			
			txtIdTipoDoc.setFocus(true);
			txtDescripcion.setEnabled(true);
			txtDescripcion.selectAll();
			txtAbreviatura.setEnabled(true);
			txtIdTipoDoc.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		//txtId.setText(null);
		txtDescripcion.setText(null);
		txtAbreviatura.setText(null);
		txtIdTipoDoc.setText(null);
	}

	@Override
	public void goToUiTipoDocIden() {
		// TODO Auto-generated method stub

	}

	public void setBean(TipoDocIdenProxy bean) {
		this.bean = bean;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(txtAbreviatura)) {
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

}
