package com.lg.client.view.uimantelectro;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ElectroProxy;
import com.lg.client.requestfactory.CntxMantElectro;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uielectro.UiHomeElectro;

public class UiMantElectroImpl extends UiMantElectro{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeElectro uiHomeElectro;

    public UiMantElectroImpl(UiHomeElectro uiHomeElectro) {        
        this.uiHomeElectro = uiHomeElectro;
    }

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantElectro context = FACTORY.cntxMantElectro();
        FACTORY.initialize(EVENTBUS);
        ElectroProxy bean = context.create(ElectroProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setIdElectro(txtAbreviatura.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        Request<Boolean> request = context.insertarElectro(bean);
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
        CntxMantElectro context = FACTORY.cntxMantElectro();
        FACTORY.initialize(EVENTBUS);        
        ElectroProxy bean = context.create(ElectroProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setCodeElectro(this.bean.getIdElectro());
        Request<Boolean> request = context.actualizarElectro(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiElectro();
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
        CntxMantElectro context = FACTORY.cntxMantElectro();
        FACTORY.initialize(EVENTBUS);       
        ElectroProxy bean = context.create(ElectroProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeElectro(this.bean.getIdElectro());  
        bean.setAbreviatura(this.bean.getAbreviatura());
        Request<Boolean> request = context.eliminarElectro(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiElectro();
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
    	uiHomeElectro.getContainer().showWidget(0);        
    	uiHomeElectro.getUiElectro().loadTable();
    }
    
    @Override
    public void goToUiElectro() {
        cleanForm();
        uiHomeElectro.getContainer().showWidget(0);
        uiHomeElectro.getUiElectro().loadTable();
    }
}
