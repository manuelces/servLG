package com.lg.client.view.uisubfamilia;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.SubFamiliaProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantSubFamilia;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uisubfamilia.UiSubFamilia;
import com.lg.client.view.uisubfamilia.UiHomeSubFamilia;

public class UiSubFamiliaImpl extends UiSubFamilia{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeSubFamilia uiHomeSubFamilia;


    public UiSubFamiliaImpl(UiHomeSubFamilia uiHomeSubFamilia) {
    	this.uiHomeSubFamilia = uiHomeSubFamilia;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantSubFamilia context = FACTORY.cntxMantSubFamilia();
        FACTORY.initialize(EVENTBUS);
        Request<List<SubFamiliaProxy>> request = context.listarSubFamilia().with("beanFamilia");
        request.fire(new Receiver<List<SubFamiliaProxy>>() {

            @Override
            public void onSuccess(List<SubFamiliaProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeSubFamilia.getContainer().showWidget(1);
        uiHomeSubFamilia.getUiMantSubFamilia().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeSubFamilia.getUiMantSubFamilia().setBean(null);        
        uiHomeSubFamilia.getUiMantSubFamilia().loadFields();
        uiHomeSubFamilia.getUiMantSubFamilia().loadFamilia();
        uiHomeSubFamilia.getUiMantSubFamilia().cleanForm();
        uiHomeSubFamilia.getUiMantSubFamilia().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	SubFamiliaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSubFamilia.getContainer().showWidget(1);
        uiHomeSubFamilia.getUiMantSubFamilia().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeSubFamilia.getUiMantSubFamilia().setBean(bean);
        uiHomeSubFamilia.getUiMantSubFamilia().loadFields();
        uiHomeSubFamilia.getUiMantSubFamilia().loadFamilia();
        uiHomeSubFamilia.getUiMantSubFamilia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	SubFamiliaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSubFamilia.getContainer().showWidget(1);
        uiHomeSubFamilia.getUiMantSubFamilia().setModo(UiMantenimiento.MODODELETE);
        uiHomeSubFamilia.getUiMantSubFamilia().setBean(bean);
        uiHomeSubFamilia.getUiMantSubFamilia().loadFields();
        uiHomeSubFamilia.getUiMantSubFamilia().loadFamilia();
        uiHomeSubFamilia.getUiMantSubFamilia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	SubFamiliaProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSubFamilia.getContainer().showWidget(1);
        uiHomeSubFamilia.getUiMantSubFamilia().setModo(UiMantenimiento.MODODETALLE);
        uiHomeSubFamilia.getUiMantSubFamilia().setBean(bean);
        uiHomeSubFamilia.getUiMantSubFamilia().loadFields();
        uiHomeSubFamilia.getUiMantSubFamilia().loadFamilia();
        uiHomeSubFamilia.getUiMantSubFamilia().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }
}
