package com.lg.client.view.uifamilia;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.FamiliaProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantFamilia;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
public class UiFamiliaImpl extends UiFamilia{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeFamilia uiHomeFamilia;


    public UiFamiliaImpl(UiHomeFamilia uiHomeFamilia) {
    	this.uiHomeFamilia = uiHomeFamilia;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantFamilia context = FACTORY.cntxMantFamilia();
        FACTORY.initialize(EVENTBUS);
        Request<List<FamiliaProxy>> request = context.listarFamilia();
        request.fire(new Receiver<List<FamiliaProxy>>() {

            @Override
            public void onSuccess(List<FamiliaProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeFamilia.getContainer().showWidget(1);
        uiHomeFamilia.getUiMantFamilia().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeFamilia.getUiMantFamilia().setBean(null);        
        uiHomeFamilia.getUiMantFamilia().loadFields();
        uiHomeFamilia.getUiMantFamilia().cleanForm();
        uiHomeFamilia.getUiMantFamilia().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	FamiliaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeFamilia.getContainer().showWidget(1);
        uiHomeFamilia.getUiMantFamilia().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeFamilia.getUiMantFamilia().setBean(bean);
        uiHomeFamilia.getUiMantFamilia().loadFields();
        uiHomeFamilia.getUiMantFamilia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	FamiliaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeFamilia.getContainer().showWidget(1);
        uiHomeFamilia.getUiMantFamilia().setModo(UiMantenimiento.MODODELETE);
        uiHomeFamilia.getUiMantFamilia().setBean(bean);
        uiHomeFamilia.getUiMantFamilia().loadFields();
        uiHomeFamilia.getUiMantFamilia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	FamiliaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeFamilia.getContainer().showWidget(1);
        uiHomeFamilia.getUiMantFamilia().setModo(UiMantenimiento.MODODETALLE);
        uiHomeFamilia.getUiMantFamilia().setBean(bean);
        uiHomeFamilia.getUiMantFamilia().loadFields();
        uiHomeFamilia.getUiMantFamilia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }
}
