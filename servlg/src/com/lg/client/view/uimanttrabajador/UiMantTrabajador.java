package com.lg.client.view.uimanttrabajador;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.view.listboxmodel.ListModelTipoTrabajador;
import com.lg.client.view.uimanttrabajador.InterUiMantTrabajador;

public class UiMantTrabajador extends UiMantenimiento implements
		InterUiMantTrabajador, KeyUpHandler {
	protected MTextBox txtId;
	protected MTextBox txtPaterno;
	protected MTextBox txtMaterno;
	protected MTextBox txtNombre;
	protected MTextBox txtDni;
	protected ListModelTipoTrabajador lstTipoTrabajador;
	protected TrabajadorProxy bean;

	public UiMantTrabajador() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtId = new MTextBox();
		txtPaterno = new MTextBox();
		txtMaterno = new MTextBox();
		txtNombre = new MTextBox();
		txtDni = new MTextBox();
		lstTipoTrabajador = new ListModelTipoTrabajador();
		// this.addWidget("ID", txtId);
		this.addWidget("PATERNO (*)", txtPaterno);
		this.addWidget("MATERNO (*)", txtMaterno);
		this.addWidget("NOMBRES (*)", txtNombre);
		this.addWidget("DNI (*)", txtDni);
		this.addWidget("TIPO TRABAJADOR (*)", lstTipoTrabajador);
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
		//txtDni.addKeyUpHandler(this);
	}

	private void initStyle() {
		lstTipoTrabajador.setWidth("100%");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			txtId.setText(this.bean.getIdTrabajador().toString());
			txtPaterno.setText(this.bean.getPaterno());
			txtMaterno.setText(this.bean.getMaterno());
			txtNombre.setText(this.bean.getNombre());
			txtDni.setText(this.bean.getDni());
			txtId.setEnabled(false);
			txtPaterno.setEnabled(true);
			txtMaterno.setEnabled(true);
			txtNombre.setEnabled(true);
			txtDni.setEnabled(true);
			txtPaterno.setFocus(true);
			txtPaterno.selectAll();
			lstTipoTrabajador.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE) || this.modo.equals("ACTIVAR") || this.modo.equals("DESACTIVAR")) {
			txtId.setText(this.bean.getIdTrabajador().toString());
			txtPaterno.setText(this.bean.getPaterno());
			txtMaterno.setText(this.bean.getMaterno());
			txtNombre.setText(this.bean.getNombre());
			txtDni.setText(this.bean.getDni());
			txtId.setEnabled(false);
			txtPaterno.setEnabled(false);
			txtMaterno.setEnabled(false);
			txtNombre.setEnabled(false);
			txtDni.setEnabled(false);
			lstTipoTrabajador.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			txtId.setText(this.bean.getIdTrabajador().toString());
			txtPaterno.setText(this.bean.getPaterno());
			txtMaterno.setText(this.bean.getMaterno());
			txtNombre.setText(this.bean.getNombre());
			txtDni.setText(this.bean.getDni());
			txtId.setEnabled(false);
			txtPaterno.setEnabled(false);
			txtMaterno.setEnabled(false);
			txtNombre.setEnabled(false);
			txtDni.setEnabled(false);
			lstTipoTrabajador.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {
			txtId.setEnabled(false);
			txtPaterno.setFocus(true);
			txtPaterno.setEnabled(true);
			txtPaterno.selectAll();
			txtMaterno.setEnabled(true);
			txtNombre.setEnabled(true);
			txtDni.setEnabled(true);
			lstTipoTrabajador.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
		txtPaterno.setText(null);
		txtMaterno.setText(null);
		txtNombre.setText(null);
		txtDni.setText(null);
	}

	@Override
	public void goToUiTrabajador() {
		// TODO Auto-generated method stub

	}

	public void setBean(TrabajadorProxy bean) {
		this.bean = bean;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(txtDni)) {
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
				if (modo.equalsIgnoreCase(MODOINSERTAR)) {
					processInsertar();
				} else if (modo.equalsIgnoreCase(MODOUPDATE)) {
					processActualizar();
				} else if (modo.equalsIgnoreCase("ACTIVAR")) {
					processActivar();
				} else if (modo.equalsIgnoreCase("DESACTIVAR")) {
					processDesactivar();
				} else {
					Window.alert("Operacion no contemplada");
				}
			}
		}
	}

	@Override
	public void loadTipoTrabajador() {
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
            }else {
                Window.alert("Operaci�n no contemplada");
            }
        }
    }
}
