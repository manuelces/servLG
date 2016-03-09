package com.lg.client.view.uimantmarca;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.MarcaProxy;
import com.lg.client.requestfactory.CntxMantMarca;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uimarca.UiHomeMarca;

public class UiMantMarcaImpl extends UiMantMarca{
	
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeMarca uiHomeMarca;

    public UiMantMarcaImpl(UiHomeMarca uiHomeMarca) {        
        this.uiHomeMarca = uiHomeMarca;
    }

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantMarca context = FACTORY.cntxMantMarca();
        FACTORY.initialize(EVENTBUS);
        MarcaProxy bean = context.create(MarcaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setIdMarca(txtAbreviatura.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        Request<Boolean> request = context.insertarMarca(bean);
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
        CntxMantMarca context = FACTORY.cntxMantMarca();
        FACTORY.initialize(EVENTBUS);        
        MarcaProxy bean = context.create(MarcaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setCodeMarca(this.bean.getIdMarca());
        Request<Boolean> request = context.actualizarMarca(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiMarca();
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
        CntxMantMarca context = FACTORY.cntxMantMarca();
        FACTORY.initialize(EVENTBUS);       
        MarcaProxy bean = context.create(MarcaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeMarca(this.bean.getIdMarca());          
        Request<Boolean> request = context.eliminarMarca(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiMarca();
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
    	uiHomeMarca.getContainer().showWidget(0);        
    	uiHomeMarca.getUiMarca().loadTable();
    }
    
    @Override
    public void goToUiMarca() {
        cleanForm();
        uiHomeMarca.getContainer().showWidget(0);
        uiHomeMarca.getUiMarca().loadTable();
    }

}
