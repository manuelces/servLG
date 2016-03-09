package com.lg.client.view.uiprovincia;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantProvincia;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiProvinciaImpl  extends UiProvincia{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeProvincia uiHomeProvincia;


    public UiProvinciaImpl(UiHomeProvincia uiHomeProvincia) {
    	this.uiHomeProvincia = uiHomeProvincia;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantProvincia context = FACTORY.cntxMantProvincia();
        FACTORY.initialize(EVENTBUS);
        Request<List<ProvinciaProxy>> request = context.listarProvincia().with("beanDepartamento");
        request.fire(new Receiver<List<ProvinciaProxy>>() {

            @Override
            public void onSuccess(List<ProvinciaProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeProvincia.getContainer().showWidget(1);
        uiHomeProvincia.getUiMantProvincia().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeProvincia.getUiMantProvincia().setBean(null);        
        uiHomeProvincia.getUiMantProvincia().loadFields();
        uiHomeProvincia.getUiMantProvincia().loadPais();;
        uiHomeProvincia.getUiMantProvincia().cleanForm();
        uiHomeProvincia.getUiMantProvincia().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	ProvinciaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeProvincia.getContainer().showWidget(1);
        uiHomeProvincia.getUiMantProvincia().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeProvincia.getUiMantProvincia().setBean(bean);
        uiHomeProvincia.getUiMantProvincia().loadFields();
        uiHomeProvincia.getUiMantProvincia().loadPais();;
        uiHomeProvincia.getUiMantProvincia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	ProvinciaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeProvincia.getContainer().showWidget(1);
        uiHomeProvincia.getUiMantProvincia().setModo(UiMantenimiento.MODODELETE);
        uiHomeProvincia.getUiMantProvincia().setBean(bean);
        uiHomeProvincia.getUiMantProvincia().loadFields();
        uiHomeProvincia.getUiMantProvincia().loadPais();
        uiHomeProvincia.getUiMantProvincia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	ProvinciaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeProvincia.getContainer().showWidget(1);
        uiHomeProvincia.getUiMantProvincia().setModo(UiMantenimiento.MODODETALLE);
        uiHomeProvincia.getUiMantProvincia().setBean(bean);
        uiHomeProvincia.getUiMantProvincia().loadFields();
        uiHomeProvincia.getUiMantProvincia().loadPais();
        uiHomeProvincia.getUiMantProvincia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }
}
