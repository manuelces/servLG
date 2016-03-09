package com.lg.client.view.uimantprovincia;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.view.listboxmodel.ListModelDepartamento;
import com.lg.client.view.listboxmodel.ListModelPais;

public class UiMantProvincia extends UiMantenimiento implements InterUiMantProvincia, KeyUpHandler,ChangeHandler{
	protected MTextBox txtId;
	protected MTextBox txtCodigo;
	protected MTextBox txtDescripcion;	
	protected ListModelPais lstPais;
	protected ListModelDepartamento lstDepartamento;
	protected ProvinciaProxy bean;
	
	public UiMantProvincia(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		txtId=new MTextBox();
		txtCodigo=new MTextBox();
		txtDescripcion=new MTextBox();	
		lstPais=new ListModelPais();
		lstDepartamento=new ListModelDepartamento();
		//this.addWidget("ID", txtId);        
        this.addWidget("CODIGO (*)", txtCodigo);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
        this.addWidget("PAIS (*)", lstPais);
        this.addWidget("DEPARTAMENTO (*)", lstDepartamento);
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
        //lstPais.addChangeHandler(this);        
    }
	
	private void initListener(){
		txtDescripcion.addKeyUpHandler(this);
		lstPais.addChangeHandler(this);
	}
	
	private void initStyle(){
		lstDepartamento.setWidth("100%");
		lstPais.setWidth("100%");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdProvincia().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);
            txtDescripcion.selectAll();
            txtCodigo.setText(this.bean.getCodigo());            
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdProvincia().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtCodigo.setText(this.bean.getCodigo());
            txtCodigo.setEnabled(false);            
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdProvincia().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtCodigo.setText(this.bean.getCodigo());
            txtCodigo.setEnabled(false);            
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtCodigo.setFocus(true);
            txtDescripcion.setEnabled(true);
            txtDescripcion.selectAll();
            txtCodigo.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
        txtDescripcion.setText(null);
        txtCodigo.setText(null);
	}

	@Override
	public void goToUiProvincia() {
		// TODO Auto-generated method stub
		
	}

	public void setBean(ProvinciaProxy bean) {
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

	@Override
	public void loadDepartamento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadPais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChange(ChangeEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(lstPais)){
			loadDepartamento(); 
		}
	}
}
