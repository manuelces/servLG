package com.lg.client.view.uitiposervicio;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.TipoServicioProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantTipoServicio;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.util.PopupProgress;

public class UiTipoServicioImpl extends UiTipoServicio{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeTipoServicio uiHomeTipoServicio;
    private PopupProgress popup=new PopupProgress();


    public UiTipoServicioImpl(UiHomeTipoServicio uiHomeTipoServicio) {
    	this.uiHomeTipoServicio = uiHomeTipoServicio;
        loadTable();
    }

    @Override
    public void loadTable() {
    	popup.showPopup();
        CntxMantTipoServicio context = FACTORY.cntxMantTipoServicio();
        FACTORY.initialize(EVENTBUS);
        Request<List<TipoServicioProxy>> request = context.listarTipoServicio().with("beanEmpresaFabricante");
        request.fire(new Receiver<List<TipoServicioProxy>>() {

            @Override
            public void onSuccess(List<TipoServicioProxy> response) {
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
        uiHomeTipoServicio.getContainer().showWidget(1);
        uiHomeTipoServicio.getUiMantTipoServicio().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeTipoServicio.getUiMantTipoServicio().setBean(null);        
        uiHomeTipoServicio.getUiMantTipoServicio().loadFields();
        uiHomeTipoServicio.getUiMantTipoServicio().loadEmpresaFabricante();
        uiHomeTipoServicio.getUiMantTipoServicio().cleanForm();
        uiHomeTipoServicio.getUiMantTipoServicio().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        TipoServicioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeTipoServicio.getContainer().showWidget(1);
        uiHomeTipoServicio.getUiMantTipoServicio().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeTipoServicio.getUiMantTipoServicio().setBean(bean);
        uiHomeTipoServicio.getUiMantTipoServicio().loadFields();
        uiHomeTipoServicio.getUiMantTipoServicio().loadEmpresaFabricante();
        uiHomeTipoServicio.getUiMantTipoServicio().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        TipoServicioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeTipoServicio.getContainer().showWidget(1);
        uiHomeTipoServicio.getUiMantTipoServicio().setModo(UiMantenimiento.MODODELETE);
        uiHomeTipoServicio.getUiMantTipoServicio().setBean(bean);
        uiHomeTipoServicio.getUiMantTipoServicio().loadFields();
        uiHomeTipoServicio.getUiMantTipoServicio().loadEmpresaFabricante();
        uiHomeTipoServicio.getUiMantTipoServicio().scrollPanel.refresh();
        }else{
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        TipoServicioProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeTipoServicio.getContainer().showWidget(1);
        uiHomeTipoServicio.getUiMantTipoServicio().setModo(UiMantenimiento.MODODETALLE);
        uiHomeTipoServicio.getUiMantTipoServicio().setBean(bean);
        uiHomeTipoServicio.getUiMantTipoServicio().loadFields();
        uiHomeTipoServicio.getUiMantTipoServicio().loadEmpresaFabricante();
        uiHomeTipoServicio.getUiMantTipoServicio().scrollPanel.refresh();
        }else{
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }
}
