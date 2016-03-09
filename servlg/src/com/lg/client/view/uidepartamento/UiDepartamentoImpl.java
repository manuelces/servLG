package com.lg.client.view.uidepartamento;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantDepartamento;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiDepartamentoImpl extends UiDepartamento{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeDepartamento uiHomeDepartamento;


    public UiDepartamentoImpl(UiHomeDepartamento uiHomeDepartamento) {
    	this.uiHomeDepartamento = uiHomeDepartamento;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantDepartamento context = FACTORY.cntxMantDepartamento();
        FACTORY.initialize(EVENTBUS);
        Request<List<DepartamentoProxy>> request = context.listarDepartamento().with("beanPais");
        request.fire(new Receiver<List<DepartamentoProxy>>() {

            @Override
            public void onSuccess(List<DepartamentoProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeDepartamento.getContainer().showWidget(1);
        uiHomeDepartamento.getUiMantDepartamento().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeDepartamento.getUiMantDepartamento().setBean(null);        
        uiHomeDepartamento.getUiMantDepartamento().loadFields();
        uiHomeDepartamento.getUiMantDepartamento().loadPais();
        uiHomeDepartamento.getUiMantDepartamento().cleanForm();
        uiHomeDepartamento.getUiMantDepartamento().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
    	DepartamentoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeDepartamento.getContainer().showWidget(1);
        uiHomeDepartamento.getUiMantDepartamento().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeDepartamento.getUiMantDepartamento().setBean(bean);
        uiHomeDepartamento.getUiMantDepartamento().loadFields();
        uiHomeDepartamento.getUiMantDepartamento().loadPais();
        uiHomeDepartamento.getUiMantDepartamento().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
    	DepartamentoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeDepartamento.getContainer().showWidget(1);
        uiHomeDepartamento.getUiMantDepartamento().setModo(UiMantenimiento.MODODELETE);
        uiHomeDepartamento.getUiMantDepartamento().setBean(bean);
        uiHomeDepartamento.getUiMantDepartamento().loadFields();
        uiHomeDepartamento.getUiMantDepartamento().loadPais();
        uiHomeDepartamento.getUiMantDepartamento().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
    	DepartamentoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeDepartamento.getContainer().showWidget(1);
        uiHomeDepartamento.getUiMantDepartamento().setModo(UiMantenimiento.MODODETALLE);
        uiHomeDepartamento.getUiMantDepartamento().setBean(bean);
        uiHomeDepartamento.getUiMantDepartamento().loadFields();
        uiHomeDepartamento.getUiMantDepartamento().loadPais();
        uiHomeDepartamento.getUiMantDepartamento().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }
}
