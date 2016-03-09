package com.lg.client.view.uimantprovincia;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.client.requestfactory.CntxMantPais;
import com.lg.client.requestfactory.CntxMantProvincia;
import com.lg.client.requestfactory.CntxMantDepartamento;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uiprovincia.UiHomeProvincia;

public class UiMantProvinciaImpl extends UiMantProvincia{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeProvincia uiHomeProvincia;

    public UiMantProvinciaImpl(UiHomeProvincia uiHomeProvincia) {        
        this.uiHomeProvincia = uiHomeProvincia;
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
            	if (!response.isEmpty()) {
            	lstPais.setData(response);
            	if(bean!=null){
            		lstPais.setSelectedItem(bean.getCodePais());
            	}
            	loadDepartamento();
            	}
            }
        });
	}
    
    @Override
	public void loadDepartamento() {
		// TODO Auto-generated method stub
    	CntxMantDepartamento context = FACTORY.cntxMantDepartamento();
        FACTORY.initialize(EVENTBUS);
        Request<List<DepartamentoProxy>> request = context.listarDepartamento(lstPais.getSelectedItem().getIdPais());
        request.fire(new Receiver<List<DepartamentoProxy>>() {

            @Override
            public void onSuccess(List<DepartamentoProxy> response) {
            	lstDepartamento.setData(response);
            	if(bean!=null){
            		lstDepartamento.setSelectedItem(bean.getBeanDepartamento().getIdDepartamento());
            	}
            }
        });
	}

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantProvincia context = FACTORY.cntxMantProvincia();
        FACTORY.initialize(EVENTBUS);
        ProvinciaProxy bean = context.create(ProvinciaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setIdProvincia(lstDepartamento.getSelectedItem().getIdDepartamento());
        bean.setCodeDepartamento(lstDepartamento.getSelectedItem().getIdDepartamento());
        bean.setBeanDepartamento(lstDepartamento.getSelectedItem());
        bean.setCodePais(lstPais.getSelectedItem().getIdPais());        
        Request<Boolean> request = context.insertarProvincia(bean);
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
        CntxMantProvincia context = FACTORY.cntxMantProvincia();
        FACTORY.initialize(EVENTBUS);        
        ProvinciaProxy bean = context.create(ProvinciaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");        
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        bean.setCodeProvincia(this.bean.getIdProvincia());
        bean.setBeanDepartamento(lstDepartamento.getSelectedItem());   
        bean.setCodeDepartamento(lstDepartamento.getSelectedItem().getIdDepartamento());
        Request<Boolean> request = context.actualizarProvincia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiProvincia();
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
        CntxMantProvincia context = FACTORY.cntxMantProvincia();
        FACTORY.initialize(EVENTBUS);       
        ProvinciaProxy bean = context.create(ProvinciaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeProvincia(this.bean.getIdProvincia());          
        Request<Boolean> request = context.eliminarProvincia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiProvincia();
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
    	uiHomeProvincia.getContainer().showWidget(0);        
    	uiHomeProvincia.getUiProvincia().loadTable();
    }
    
    @Override
    public void goToUiProvincia() {
        cleanForm();
        uiHomeProvincia.getContainer().showWidget(0);
        uiHomeProvincia.getUiProvincia().loadTable();
    }
}
