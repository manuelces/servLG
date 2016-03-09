package com.lg.client.view.uimantfamilia;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.FamiliaProxy;
import com.lg.client.requestfactory.CntxMantFamilia;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uifamilia.UiHomeFamilia;

public class UiMantFamiliaImpl extends UiMantFamilia{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeFamilia uiHomeFamilia;

    public UiMantFamiliaImpl(UiHomeFamilia uiHomeFamilia) {        
        this.uiHomeFamilia = uiHomeFamilia;
    }

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantFamilia context = FACTORY.cntxMantFamilia();
        FACTORY.initialize(EVENTBUS);
        FamiliaProxy bean = context.create(FamiliaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setIdFamilia(txtAbreviatura.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        Request<Boolean> request = context.insertarFamilia(bean);
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
        CntxMantFamilia context = FACTORY.cntxMantFamilia();
        FACTORY.initialize(EVENTBUS);        
        FamiliaProxy bean = context.create(FamiliaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setCodeFamilia(this.bean.getIdFamilia());
        Request<Boolean> request = context.actualizarFamilia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiFamilia();
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
        CntxMantFamilia context = FACTORY.cntxMantFamilia();
        FACTORY.initialize(EVENTBUS);       
        FamiliaProxy bean = context.create(FamiliaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeFamilia(this.bean.getIdFamilia());  
        bean.setAbreviatura(this.bean.getAbreviatura());
        Request<Boolean> request = context.eliminarFamilia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiFamilia();
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
    	uiHomeFamilia.getContainer().showWidget(0);        
    	uiHomeFamilia.getUiFamilia().loadTable();
    }
    
    @Override
    public void goToUiFamilia() {
        cleanForm();
        uiHomeFamilia.getContainer().showWidget(0);
        uiHomeFamilia.getUiFamilia().loadTable();
    }
}
