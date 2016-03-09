package com.lg.client.view.uimantempresafabricante;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
import com.lg.client.requestfactory.CntxMantEmpresaFabricante;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uiempresafabricante.UiHomeEmpresaFabricante;

public class UiMantEmpresaFabricanteImpl extends UiMantEmpresaFabricante{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeEmpresaFabricante uiHomeEmpresaFabricante;

    public UiMantEmpresaFabricanteImpl(UiHomeEmpresaFabricante uiHomeEmpresaFabricante) {        
        this.uiHomeEmpresaFabricante = uiHomeEmpresaFabricante;
    }

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantEmpresaFabricante context = FACTORY.cntxMantEmpresaFabricante();
        FACTORY.initialize(EVENTBUS);
        EmpresaFabricanteProxy bean = context.create(EmpresaFabricanteProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setIdEmpresaFabricante(txtRuc.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setRuc(txtRuc.getText().toUpperCase());
        Request<Boolean> request = context.insertarEmpresaFabricante(bean);
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
        CntxMantEmpresaFabricante context = FACTORY.cntxMantEmpresaFabricante();
        FACTORY.initialize(EVENTBUS);        
        EmpresaFabricanteProxy bean = context.create(EmpresaFabricanteProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setRuc(txtRuc.getText().toUpperCase());
        bean.setCodeEmpresaFabricante(this.bean.getIdEmpresaFabricante());
        Request<Boolean> request = context.actualizarEmpresaFabricante(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiEmpresaFabricante();
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
        CntxMantEmpresaFabricante context = FACTORY.cntxMantEmpresaFabricante();
        FACTORY.initialize(EVENTBUS);       
        EmpresaFabricanteProxy bean = context.create(EmpresaFabricanteProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeEmpresaFabricante(this.bean.getIdEmpresaFabricante());  
        bean.setRuc(this.bean.getRuc());
        Request<Boolean> request = context.eliminarEmpresaFabricante(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiEmpresaFabricante();
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
    	uiHomeEmpresaFabricante.getContainer().showWidget(0);        
    	uiHomeEmpresaFabricante.getUiEmpresaFabricante().loadTable();
    }
    
    @Override
    public void goToUiEmpresaFabricante() {
        cleanForm();        
        uiHomeEmpresaFabricante.getContainer().showWidget(0);
        uiHomeEmpresaFabricante.getUiEmpresaFabricante().loadTable();
    }
}
