package com.lg.client.view.uimantdistrito;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.DistritoProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.view.listboxmodel.ListModelDepartamento;
import com.lg.client.view.listboxmodel.ListModelPais;
import com.lg.client.view.listboxmodel.ListModelProvincia;

public class UiMantDistrito extends UiMantenimiento implements InterUiMantDistrito, KeyUpHandler,ChangeHandler{
	protected MTextBox txtId;
	protected MTextBox txtCodigo;
	protected MTextBox txtDescripcion;	
	protected ListModelProvincia lstProvincia;
	protected ListModelDepartamento lstDepartamento;
	protected ListModelPais lstPais;
	protected DistritoProxy bean;
	
	public UiMantDistrito(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		txtId=new MTextBox();
		txtCodigo=new MTextBox();
		txtDescripcion=new MTextBox();	
		lstProvincia=new ListModelProvincia();
		lstDepartamento=new ListModelDepartamento();
		lstPais=new ListModelPais();
		//this.addWidget("ID", txtId);                
        this.addWidget("PAIS (*)", lstPais);
        this.addWidget("DEPARTAMENTO (*)", lstDepartamento);
        this.addWidget("PROVINCIA (*)", lstProvincia);
        this.addWidget("CODIGO (*)", txtCodigo);
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
	
	private void initListener(){
		txtDescripcion.addKeyUpHandler(this);
		lstPais.addChangeHandler(this);
		lstDepartamento.addChangeHandler(this);
		lstProvincia.addChangeHandler(this);
	}
	
	private void initStyle(){
		lstPais.setWidth("100%");
		lstDepartamento.setWidth("100%");
		lstProvincia.setWidth("100%");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdDistrito().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);
            txtDescripcion.selectAll();
            txtCodigo.setText(this.bean.getCodigo());            
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdDistrito().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);
            txtCodigo.setText(this.bean.getCodigo());
            txtCodigo.setEnabled(false);            
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdDistrito().toString());
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
	public void goToUiDistrito() {
		// TODO Auto-generated method stub
		
	}

	public void setBean(DistritoProxy bean) {
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
	public void loadProvincia() {
		// TODO Auto-generated method stub
		
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
		}else if(event.getSource().equals(lstDepartamento)){
			loadProvincia();
		}
	}
}
