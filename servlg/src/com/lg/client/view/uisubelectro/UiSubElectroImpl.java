package com.lg.client.view.uisubelectro;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.SubElectroProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantSubElectro;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uisubelectro.UiSubElectro;
import com.lg.client.view.uisubelectro.UiHomeSubElectro;

public class UiSubElectroImpl extends UiSubElectro{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeSubElectro uiHomeSubElectro;


    public UiSubElectroImpl(UiHomeSubElectro uiHomeSubElectro) {
    	this.uiHomeSubElectro = uiHomeSubElectro;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantSubElectro context = FACTORY.cntxMantSubElectro();
        FACTORY.initialize(EVENTBUS);
        Request<List<SubElectroProxy>> request = context.listarSubElectro().with("beanElectro");
        request.fire(new Receiver<List<SubElectroProxy>>() {

            @Override
            public void onSuccess(List<SubElectroProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeSubElectro.getContainer().showWidget(1);
        uiHomeSubElectro.getUiMantSubElectro().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeSubElectro.getUiMantSubElectro().setBean(null);        
        uiHomeSubElectro.getUiMantSubElectro().loadFields();
        uiHomeSubElectro.getUiMantSubElectro().loadElectro();
        uiHomeSubElectro.getUiMantSubElectro().cleanForm();
        uiHomeSubElectro.getUiMantSubElectro().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	SubElectroProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSubElectro.getContainer().showWidget(1);
        uiHomeSubElectro.getUiMantSubElectro().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeSubElectro.getUiMantSubElectro().setBean(bean);
        uiHomeSubElectro.getUiMantSubElectro().loadFields();
        uiHomeSubElectro.getUiMantSubElectro().loadElectro();
        uiHomeSubElectro.getUiMantSubElectro().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	SubElectroProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSubElectro.getContainer().showWidget(1);
        uiHomeSubElectro.getUiMantSubElectro().setModo(UiMantenimiento.MODODELETE);
        uiHomeSubElectro.getUiMantSubElectro().setBean(bean);
        uiHomeSubElectro.getUiMantSubElectro().loadFields();
        uiHomeSubElectro.getUiMantSubElectro().loadElectro();
        uiHomeSubElectro.getUiMantSubElectro().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	SubElectroProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeSubElectro.getContainer().showWidget(1);
        uiHomeSubElectro.getUiMantSubElectro().setModo(UiMantenimiento.MODODETALLE);
        uiHomeSubElectro.getUiMantSubElectro().setBean(bean);
        uiHomeSubElectro.getUiMantSubElectro().loadFields();
        uiHomeSubElectro.getUiMantSubElectro().loadElectro();
        uiHomeSubElectro.getUiMantSubElectro().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }
}
