package com.lg.client.view.uimantempresafabricante;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;

public class UiMantEmpresaFabricante extends UiMantenimiento implements InterUiMantEmpresaFabricante, KeyUpHandler{
	protected MTextBox txtId;
	protected MTextBox txtDescripcion;
	protected MTextBox txtRuc;
	protected EmpresaFabricanteProxy bean;
	
	public UiMantEmpresaFabricante(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		txtId=new MTextBox();
		txtDescripcion=new MTextBox();
		txtRuc=new MTextBox();
		//this.addWidget("ID", txtId);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
        this.addWidget("RUC (*)", txtRuc);
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
	
	private void initListener(){
		txtRuc.addKeyUpHandler(this);
	}
	
	private void initStyle(){
		
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdEmpresaFabricante().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtRuc.setText(this.bean.getRuc());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);
            txtDescripcion.selectAll();
            txtRuc.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdEmpresaFabricante().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtRuc.setText(this.bean.getRuc());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtRuc.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdEmpresaFabricante().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtRuc.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtRuc.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtDescripcion.setFocus(true);
            txtDescripcion.setEnabled(true);
            txtDescripcion.selectAll();
            txtRuc.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
        txtDescripcion.setText(null);
        txtRuc.setText(null);
	}

	@Override
	public void goToUiEmpresaFabricante() {
		// TODO Auto-generated method stub
		
	}

	public void setBean(EmpresaFabricanteProxy bean) {
		this.bean = bean;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(txtRuc)) {
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
