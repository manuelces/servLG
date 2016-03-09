package com.lg.client.view.uimantcorrelativo;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.CorrelativoProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.util.Notification;
import com.lg.client.view.listboxmodel.ListModelTipoDocumento;
import com.lg.shared.FieldVerifier;

public class UiMantCorrelativo extends UiMantenimiento implements
		InterUiMantCorrelativo, KeyUpHandler, BlurHandler, FocusHandler {
	protected MTextBox txtId;
	protected MTextBox txtSerie;
	protected MTextBox txtPreimpreso;
	protected MTextBox txtNumInicial;
	protected MTextBox txtNumFinal;
	protected ListModelTipoDocumento lstTipoDocumento;
	protected CorrelativoProxy bean;

	public UiMantCorrelativo() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtId = new MTextBox();
		txtSerie = new MTextBox();
		txtSerie.setMaxLength(4);
		txtPreimpreso = new MTextBox();
		txtPreimpreso.setMaxLength(10);
		txtNumInicial = new MTextBox();
		txtNumInicial.setMaxLength(10);
		txtNumFinal = new MTextBox();
		txtNumFinal.setMaxLength(10);
		lstTipoDocumento = new ListModelTipoDocumento();
		// this.addWidget("ID", txtId);
		this.addWidget("TIPO DOCUMENTO (*)", lstTipoDocumento);
		this.addWidget("SERIE (*)", txtSerie);
		this.addWidget("INICIAL (*)", txtNumInicial);
		this.addWidget("FINAL (*)", txtNumFinal);
		this.addWidget("PREIMPRESO (*)", txtPreimpreso);
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
		txtSerie.addKeyUpHandler(this);
		txtSerie.addBlurHandler(this);
		txtSerie.addFocusHandler(this);
		txtPreimpreso.addKeyUpHandler(this);
		txtPreimpreso.addBlurHandler(this);
		txtPreimpreso.addFocusHandler(this);
		txtNumInicial.addKeyUpHandler(this);
		txtNumInicial.addBlurHandler(this);
		txtNumInicial.addFocusHandler(this);
		txtNumFinal.addKeyUpHandler(this);
		txtNumFinal.addBlurHandler(this);
		txtNumFinal.addFocusHandler(this);
	}

	private void initStyle() {
		lstTipoDocumento.setWidth("100%");
		// txtSerie.getElement().setAttribute("type", "number");
		// txtSerie.getElement().setPropertyString("type", "number");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			txtId.setText(this.bean.getIdCorrelativo().toString());
			txtSerie.setText(this.bean.getSerie());
			txtPreimpreso.setText(this.bean.getPreimpreso());
			txtNumInicial.setText(this.bean.getNumInicial());
			txtNumFinal.setText(this.bean.getNumFinal());
			txtId.setEnabled(false);
			txtSerie.setEnabled(true);
			txtPreimpreso.setEnabled(true);
			txtNumInicial.setEnabled(true);
			txtNumFinal.setEnabled(true);
			txtSerie.setFocus(true);
			txtSerie.selectAll();
			lstTipoDocumento.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)
				|| this.modo.equals("ACTIVAR")
				|| this.modo.equals("DESACTIVAR")) {
			txtId.setText(this.bean.getIdCorrelativo().toString());
			txtSerie.setText(this.bean.getSerie());
			txtPreimpreso.setText(this.bean.getPreimpreso());
			txtNumInicial.setText(this.bean.getNumInicial());
			txtNumFinal.setText(this.bean.getNumFinal());
			txtId.setEnabled(false);
			txtSerie.setEnabled(false);
			txtPreimpreso.setEnabled(false);
			txtNumInicial.setEnabled(false);
			txtNumFinal.setEnabled(false);
			lstTipoDocumento.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			txtId.setText(this.bean.getIdCorrelativo().toString());
			txtSerie.setText(this.bean.getSerie());
			txtPreimpreso.setText(this.bean.getPreimpreso());
			txtNumInicial.setText(this.bean.getNumInicial());
			txtNumFinal.setText(this.bean.getNumFinal());
			txtId.setEnabled(false);
			txtSerie.setEnabled(false);
			txtPreimpreso.setEnabled(false);
			txtNumInicial.setEnabled(false);
			txtNumFinal.setEnabled(false);
			lstTipoDocumento.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {
			txtId.setEnabled(false);
			txtSerie.setFocus(true);
			txtPreimpreso.setEnabled(true);
			txtNumInicial.setEnabled(true);
			txtNumFinal.setEnabled(true);
			txtSerie.selectAll();
			txtPreimpreso.setEnabled(true);
			lstTipoDocumento.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
		txtSerie.setText(null);
		txtPreimpreso.setText(null);
		txtNumInicial.setText(null);
		txtNumFinal.setText(null);
	}

	@Override
	public void goToUiCorrelativo() {
		// TODO Auto-generated method stub

	}

	public void setBean(CorrelativoProxy bean) {
		this.bean = bean;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
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
		if (event.getSource().equals(txtNumInicial)) {
			if (!FieldVerifier.isCadenaNumberEntero(txtNumInicial.getText()
					.trim())) {
				txtNumInicial.setText("");
			}
		}
		if (event.getSource().equals(txtNumFinal)) {
			if (!FieldVerifier.isCadenaNumberEntero(txtNumFinal.getText()
					.trim())) {
				txtNumFinal.setText("");
			}
		}
	}

	@Override
	public void loadTipoDocumento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processActivar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub

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
			} else if (modo.equalsIgnoreCase("ACTIVAR")) {
				processActivar();
			} else if (modo.equalsIgnoreCase("DESACTIVAR")) {
				processDesactivar();
			} else {
				//Window.alert("Operaci�n no contemplada");
				Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
            	not.showPopup();
			}
		}
	}

	@Override
	public void onBlur(BlurEvent event) {
		if (event.getSource().equals(txtSerie)) {
			if (txtSerie.getText().trim().length() > 0) {
				while (txtSerie.getText().trim().length() < txtSerie
						.getMaxLength()) {
					txtSerie.setText("0" + txtSerie.getText().trim());
				}
			}
		}
		if (event.getSource().equals(txtPreimpreso)) {
			if (txtPreimpreso.getText().trim().length() > 0) {
				while (txtPreimpreso.getText().trim().length() < txtPreimpreso
						.getMaxLength()) {
					txtPreimpreso.setText("0" + txtPreimpreso.getText().trim());
				}
			}
		}
		if (event.getSource().equals(txtNumInicial)) {
			if (txtNumInicial.getText().trim().length() > 0) {
				while (txtNumInicial.getText().trim().length() < txtNumInicial
						.getMaxLength()) {
					txtNumInicial.setText("0" + txtNumInicial.getText().trim());
				}
			}
		}
		if (event.getSource().equals(txtNumFinal)) {
			if (txtNumFinal.getText().trim().length() > 0) {
				while (txtNumFinal.getText().trim().length() < txtNumFinal
						.getMaxLength()) {
					txtNumFinal.setText("0" + txtNumFinal.getText().trim());
				}
			}
		}
	}

	@Override
	public void onFocus(FocusEvent event) {
		if (event.getSource().equals(txtSerie)) {
			txtSerie.selectAll();
		}
		if (event.getSource().equals(txtPreimpreso)) {
			txtPreimpreso.selectAll();
		}
		if (event.getSource().equals(txtNumInicial)) {
			txtNumInicial.selectAll();
		}
		if (event.getSource().equals(txtNumFinal)) {
			txtNumFinal.selectAll();
		}
	}
}
