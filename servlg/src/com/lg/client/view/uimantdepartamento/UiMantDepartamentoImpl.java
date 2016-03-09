package com.lg.client.view.uimantdepartamento;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.client.requestfactory.CntxMantPais;
import com.lg.client.requestfactory.CntxMantDepartamento;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uidepartamento.UiHomeDepartamento;

public class UiMantDepartamentoImpl extends UiMantDepartamento{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeDepartamento uiHomeDepartamento;

    public UiMantDepartamentoImpl(UiHomeDepartamento uiHomeDepartamento) {        
        this.uiHomeDepartamento = uiHomeDepartamento;
        loadPais();
    }
    
    @Override
	public void loadPais() {
		// TODO Auto-generated method stub
    	CntxMantPais context = FACTORY.cntxMantPais();
        FACTORY.initialize(EVENTBUS);
        Request<List<PaisProxy>> request = context.listarPais();
        request.fire(new Receiver<List<PaisProxy>>() {

            @Override
            public void onSuccess(List<PaisProxy> response) {
            	lstPais.setData(response);
            	if(bean!=null){
            		lstPais.setSelectedItem(bean.getBeanPais().getIdPais());
            	}
            }
        });
	}

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantDepartamento context = FACTORY.cntxMantDepartamento();
        FACTORY.initialize(EVENTBUS);
        DepartamentoProxy bean = context.create(DepartamentoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setIdDepartamento(lstPais.getSelectedItem().getIdPais());
        bean.setCodePais(lstPais.getSelectedItem().getIdPais());
        bean.setBeanPais(lstPais.getSelectedItem());
        Request<Boolean> request = context.insertarDepartamento(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                cleanForm();
                //Window.alert("Insertado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
                not.showPopup();
            	}else{
            		//Window.alert("Error al insertar");
            		Notification not=new Notification(Notification.ALERT,"Error al insertar");
                    not.showPopup();
            	}
            }
        });
    }

    @Override
    public void processActualizar() {
        Date fecha = new Date();
        CntxMantDepartamento context = FACTORY.cntxMantDepartamento();
        FACTORY.initialize(EVENTBUS);        
        DepartamentoProxy bean = context.create(DepartamentoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");        
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        bean.setCodeDepartamento(this.bean.getIdDepartamento());
        bean.setBeanPais(lstPais.getSelectedItem());   
        bean.setCodePais(lstPais.getSelectedItem().getIdPais());
        Request<Boolean> request = context.actualizarDepartamento(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiDepartamento();
            	}else{
            		//Window.alert("Error al actualizar");
            		Notification not=new Notification(Notification.ALERT,"Error al actualizar");
                    not.showPopup();
            	}
            }
        });
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        CntxMantDepartamento context = FACTORY.cntxMantDepartamento();
        FACTORY.initialize(EVENTBUS);       
        DepartamentoProxy bean = context.create(DepartamentoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeDepartamento(this.bean.getIdDepartamento());          
        Request<Boolean> request = context.eliminarDepartamento(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiDepartamento();
            	}else{
            		//Window.alert("Error al eliminar");
            		Notification not=new Notification(Notification.ALERT,"Error al eliminar");
                    not.showPopup();
            	}
            }
        });
    }

    @Override
    public void goToBack() {
    	uiHomeDepartamento.getContainer().showWidget(0);        
    	uiHomeDepartamento.getUiDepartamento().loadTable();
    }
    
    @Override
    public void goToUiDepartamento() {
        cleanForm();
        uiHomeDepartamento.getContainer().showWidget(0);
        uiHomeDepartamento.getUiDepartamento().loadTable();
    }
}
