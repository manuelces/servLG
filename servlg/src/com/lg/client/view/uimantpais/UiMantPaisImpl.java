package com.lg.client.view.uimantpais;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.requestfactory.CntxMantPais;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uipais.UiHomePais;

public class UiMantPaisImpl extends UiMantPais{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomePais uiHomePais;

    public UiMantPaisImpl(UiHomePais uiHomePais) {        
        this.uiHomePais = uiHomePais;
    }

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantPais context = FACTORY.cntxMantPais();
        FACTORY.initialize(EVENTBUS);
        PaisProxy bean = context.create(PaisProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setIdPais(txtCodigo.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        Request<Boolean> request = context.insertarPais(bean);
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
        CntxMantPais context = FACTORY.cntxMantPais();
        FACTORY.initialize(EVENTBUS);        
        PaisProxy bean = context.create(PaisProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setCodigo(txtCodigo.getText().toUpperCase());        
        bean.setCodePais(this.bean.getIdPais());
        Request<Boolean> request = context.actualizarPais(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiPais();
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
        CntxMantPais context = FACTORY.cntxMantPais();
        FACTORY.initialize(EVENTBUS);       
        PaisProxy bean = context.create(PaisProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodePais(this.bean.getIdPais());  
        Request<Boolean> request = context.eliminarPais(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiPais();
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
    	uiHomePais.getContainer().showWidget(0);        
    	uiHomePais.getUiPais().loadTable();
    }
    
    @Override
    public void goToUiPais() {
        cleanForm();
        uiHomePais.getContainer().showWidget(0);
        uiHomePais.getUiPais().loadTable();
    }
}
