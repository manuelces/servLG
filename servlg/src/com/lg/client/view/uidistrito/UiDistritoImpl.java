package com.lg.client.view.uidistrito;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.DistritoProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantDistrito;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiDistritoImpl  extends UiDistrito{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeDistrito uiHomeDistrito;


    public UiDistritoImpl(UiHomeDistrito uiHomeDistrito) {
    	this.uiHomeDistrito = uiHomeDistrito;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantDistrito context = FACTORY.cntxMantDistrito();
        FACTORY.initialize(EVENTBUS);
        Request<List<DistritoProxy>> request = context.listarDistrito().with("beanProvincia");
        request.fire(new Receiver<List<DistritoProxy>>() {

            @Override
            public void onSuccess(List<DistritoProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeDistrito.getContainer().showWidget(1);
        uiHomeDistrito.getUiMantDistrito().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeDistrito.getUiMantDistrito().setBean(null);        
        uiHomeDistrito.getUiMantDistrito().loadFields();
        uiHomeDistrito.getUiMantDistrito().loadPais();
        uiHomeDistrito.getUiMantDistrito().cleanForm();
        uiHomeDistrito.getUiMantDistrito().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	DistritoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeDistrito.getContainer().showWidget(1);
        uiHomeDistrito.getUiMantDistrito().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeDistrito.getUiMantDistrito().setBean(bean);
        uiHomeDistrito.getUiMantDistrito().loadFields();
        uiHomeDistrito.getUiMantDistrito().loadPais();
        uiHomeDistrito.getUiMantDistrito().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	DistritoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeDistrito.getContainer().showWidget(1);
        uiHomeDistrito.getUiMantDistrito().setModo(UiMantenimiento.MODODELETE);
        uiHomeDistrito.getUiMantDistrito().setBean(bean);
        uiHomeDistrito.getUiMantDistrito().loadFields();
        uiHomeDistrito.getUiMantDistrito().loadPais();
        uiHomeDistrito.getUiMantDistrito().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	DistritoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeDistrito.getContainer().showWidget(1);
        uiHomeDistrito.getUiMantDistrito().setModo(UiMantenimiento.MODODETALLE);
        uiHomeDistrito.getUiMantDistrito().setBean(bean);
        uiHomeDistrito.getUiMantDistrito().loadFields();
        uiHomeDistrito.getUiMantDistrito().loadPais();
        uiHomeDistrito.getUiMantDistrito().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }
}
