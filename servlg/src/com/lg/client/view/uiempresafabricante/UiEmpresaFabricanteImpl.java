package com.lg.client.view.uiempresafabricante;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantEmpresaFabricante;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiEmpresaFabricanteImpl extends UiEmpresaFabricante{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeEmpresaFabricante uiHomeEmpresaFabricante;


    public UiEmpresaFabricanteImpl(UiHomeEmpresaFabricante uiHomeEmpresaFabricante) {
    	this.uiHomeEmpresaFabricante = uiHomeEmpresaFabricante;
        loadTable();
    }

    @Override
    public void loadTable() {
        CntxMantEmpresaFabricante context = FACTORY.cntxMantEmpresaFabricante();
        FACTORY.initialize(EVENTBUS);
        Request<List<EmpresaFabricanteProxy>> request = context.listarEmpresaFabricante();
        request.fire(new Receiver<List<EmpresaFabricanteProxy>>() {

            @Override
            public void onSuccess(List<EmpresaFabricanteProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);
                //lstBdEmpresa.setData(grid.getData());
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeEmpresaFabricante.getContainer().showWidget(1);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setBean(null);        
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().loadFields();
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().cleanForm();
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        EmpresaFabricanteProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeEmpresaFabricante.getContainer().showWidget(1);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setBean(bean);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().loadFields();
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        EmpresaFabricanteProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeEmpresaFabricante.getContainer().showWidget(1);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setModo(UiMantenimiento.MODODELETE);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setBean(bean);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().loadFields();
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        EmpresaFabricanteProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeEmpresaFabricante.getContainer().showWidget(1);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setModo(UiMantenimiento.MODODETALLE);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().setBean(bean);
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().loadFields();
        uiHomeEmpresaFabricante.getUiMantEmpresaFabricante().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
        	not.showPopup();
        }
    }
}
