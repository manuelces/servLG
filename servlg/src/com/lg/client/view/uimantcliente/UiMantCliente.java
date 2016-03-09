package com.lg.client.view.uimantcliente;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.lg.client.beanproxy.ClienteProxy;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.util.Notification;
import com.lg.client.view.listboxmodel.ListModelDepartamento;
import com.lg.client.view.listboxmodel.ListModelDistrito;
import com.lg.client.view.listboxmodel.ListModelPais;
import com.lg.client.view.listboxmodel.ListModelProvincia;
import com.lg.client.view.listboxmodel.ListModelTipoDocIden;

public class UiMantCliente extends UiMantenimiento implements InterUiMantCliente, KeyUpHandler,ChangeHandler{
	protected MTextBox txtId;
	protected ListModelTipoDocIden lstTipoDocIden;
	protected MTextBox txtDocumento;
	protected MTextBox txtDescripcion;
	protected ListModelPais lstPais;
	protected ListModelDepartamento lstDepartamento;
	protected ListModelProvincia lstProvincia;	
	protected ListModelDistrito lstDistrito;
	protected MTextBox txtDireccion;
	protected MTextBox txtCelular;
	protected MTextBox txtTelefono;
	
	protected ClienteProxy bean;
	
	public UiMantCliente(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		txtId=new MTextBox();
		lstTipoDocIden=new ListModelTipoDocIden();
		txtDocumento=new MTextBox();
		txtDescripcion=new MTextBox();	
		lstPais=new ListModelPais();
		lstDepartamento=new ListModelDepartamento();
		lstProvincia=new ListModelProvincia();
		lstDistrito=new ListModelDistrito();
		txtDireccion=new MTextBox();
		txtCelular=new MTextBox();
		txtTelefono=new MTextBox();
		
		//this.addWidget("ID", txtId);        
		this.addWidget("TIPO DOC (*)", lstTipoDocIden);
        this.addWidget("DOCUMENTO (*)", txtDocumento);
        this.addWidget("DESCRIPCION (*)", txtDescripcion);
        this.addWidget("PAIS (*)", lstPais);
        this.addWidget("DEPARTAMENTO (*)", lstDepartamento);
        this.addWidget("PROVINCIA (*)", lstProvincia);
        this.addWidget("DISTRITO (*)", lstDistrito);
        this.addWidget("DIRECCION (*)", txtDireccion);
        this.addWidget("CELULAR (*)", txtCelular);
        this.addWidget("TELEFONO (*)", txtTelefono);
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
		lstTipoDocIden.setWidth("100%");
		lstPais.setWidth("100%");
		lstDepartamento.setWidth("100%");
		lstProvincia.setWidth("100%");
		lstDistrito.setWidth("100%");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
            txtId.setText(this.bean.getIdCliente().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(true);
            txtDescripcion.setFocus(true);
            txtDescripcion.selectAll();            
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {
            txtId.setText(this.bean.getIdCliente().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);           
            txtDocumento.setEnabled(false);
            this.btnOperacion.setDisabled(false);
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
            txtId.setText(this.bean.getIdCliente().toString());
            txtDescripcion.setText(this.bean.getDescripcion());
            txtId.setEnabled(false);
            txtDescripcion.setEnabled(false);           
            txtDocumento.setEnabled(false);
            this.btnOperacion.setDisabled(true);
        } else {
            txtId.setEnabled(false);
            txtDocumento.setFocus(true);
            txtDescripcion.setEnabled(true);
            txtDescripcion.selectAll();
            txtDocumento.setEnabled(true);
            this.btnOperacion.setDisabled(false);
        }
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
        txtDescripcion.setText(null);
        txtDocumento.setText(null);
        txtDireccion.setText(null);
        txtCelular.setText(null);
        txtTelefono.setText(null);
	}

	@Override
	public void goToUiCliente() {
		// TODO Auto-generated method stub
		
	}

	public void setBean(ClienteProxy bean) {
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
                    //Window.alert("Operacion no contemplada");
                	Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
                	not.showPopup();
                }
            }
        }
	}

	@Override
	public void loadTipoDocIden() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadDistrito() {
		// TODO Auto-generated method stub
		
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
		}else if(event.getSource().equals(lstProvincia)){
			loadDistrito();
		}
	}
}
