package com.lg.client.view.uicliente;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ClienteProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantCliente;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiClienteImpl extends UiCliente{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeCliente uiHomeCliente;


    public UiClienteImpl(UiHomeCliente uiHomeCliente) {
    	this.uiHomeCliente = uiHomeCliente;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantCliente context = FACTORY.cntxMantCliente();
        FACTORY.initialize(EVENTBUS);
        Request<List<ClienteProxy>> request = context.listarCliente().with("beanTipoDocIden","beanDistrito");
        request.fire(new Receiver<List<ClienteProxy>>() {

            @Override
            public void onSuccess(List<ClienteProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeCliente.getContainer().showWidget(1);
        uiHomeCliente.getUiMantCliente().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeCliente.getUiMantCliente().setBean(null);        
        uiHomeCliente.getUiMantCliente().loadFields();
        uiHomeCliente.getUiMantCliente().loadPais();
        uiHomeCliente.getUiMantCliente().loadTipoDocIden();
        uiHomeCliente.getUiMantCliente().cleanForm();
        uiHomeCliente.getUiMantCliente().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	ClienteProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeCliente.getContainer().showWidget(1);
        uiHomeCliente.getUiMantCliente().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeCliente.getUiMantCliente().setBean(bean);
        uiHomeCliente.getUiMantCliente().loadFields();
        uiHomeCliente.getUiMantCliente().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	ClienteProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeCliente.getContainer().showWidget(1);
        uiHomeCliente.getUiMantCliente().setModo(UiMantenimiento.MODODELETE);
        uiHomeCliente.getUiMantCliente().setBean(bean);
        uiHomeCliente.getUiMantCliente().loadFields();
        uiHomeCliente.getUiMantCliente().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	ClienteProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeCliente.getContainer().showWidget(1);
        uiHomeCliente.getUiMantCliente().setModo(UiMantenimiento.MODODETALLE);
        uiHomeCliente.getUiMantCliente().setBean(bean);
        uiHomeCliente.getUiMantCliente().loadFields();
        uiHomeCliente.getUiMantCliente().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }
}
