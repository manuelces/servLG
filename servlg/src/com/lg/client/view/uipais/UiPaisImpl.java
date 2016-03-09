package com.lg.client.view.uipais;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantPais;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.util.PopupProgress;

public class UiPaisImpl extends UiPais{
	private PopupProgress popup=new PopupProgress(); 
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomePais uiHomePais;


    public UiPaisImpl(UiHomePais uiHomePais) {
    	this.uiHomePais = uiHomePais;
        loadTable();
    }

    @Override
    public void loadTable() {
    	popup.showPopup();
        CntxMantPais context = FACTORY.cntxMantPais();
        FACTORY.initialize(EVENTBUS);
        Request<List<PaisProxy>> request = context.listarPais();
        request.fire(new Receiver<List<PaisProxy>>() {

            @Override
            public void onSuccess(List<PaisProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
                popup.hidePopup();
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomePais.getContainer().showWidget(1);
        uiHomePais.getUiMantPais().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomePais.getUiMantPais().setBean(null);        
        uiHomePais.getUiMantPais().loadFields();
        uiHomePais.getUiMantPais().cleanForm();
        uiHomePais.getUiMantPais().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	PaisProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomePais.getContainer().showWidget(1);
        uiHomePais.getUiMantPais().setModo(UiMantenimiento.MODOUPDATE);
        uiHomePais.getUiMantPais().setBean(bean);
        uiHomePais.getUiMantPais().loadFields();
        uiHomePais.getUiMantPais().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	PaisProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomePais.getContainer().showWidget(1);
        uiHomePais.getUiMantPais().setModo(UiMantenimiento.MODODELETE);
        uiHomePais.getUiMantPais().setBean(bean);
        uiHomePais.getUiMantPais().loadFields();
        uiHomePais.getUiMantPais().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	PaisProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomePais.getContainer().showWidget(1);
        uiHomePais.getUiMantPais().setModo(UiMantenimiento.MODODETALLE);
        uiHomePais.getUiMantPais().setBean(bean);
        uiHomePais.getUiMantPais().loadFields();
        uiHomePais.getUiMantPais().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }
}
