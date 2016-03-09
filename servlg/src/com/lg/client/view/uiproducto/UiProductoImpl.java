package com.lg.client.view.uiproducto;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ProductoProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.requestfactory.CntxMantProducto;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;

public class UiProductoImpl extends UiProducto{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeProducto uiHomeProducto;
    
    public UiProductoImpl(UiHomeProducto uiHomeProducto){
    	this.uiHomeProducto=uiHomeProducto;
    	loadTable();
    }
    
    @Override
    public void loadTable() {
        CntxMantProducto context = FACTORY.cntxMantProducto();
        FACTORY.initialize(EVENTBUS);
        Request<List<ProductoProxy>> request = context.listarProducto().with("beanSubFamilia","beanMarca","beanSubFamilia.beanFamilia");
        request.fire(new Receiver<List<ProductoProxy>>() {

            @Override
            public void onSuccess(List<ProductoProxy> response) {
                grid.getSelectionModel().clear();
                grid.setData(response);         
            }
        });
    }
    
    @Override
    public void showUIOper1() {
        // TODO Auto-generated method stub
        uiHomeProducto.getContainer().showWidget(1);
        uiHomeProducto.getUiMantProducto().setModo(UiMantenimiento.MODOINSERTAR);
        uiHomeProducto.getUiMantProducto().setBean(null);        
        uiHomeProducto.getUiMantProducto().loadFields();
        uiHomeProducto.getUiMantProducto().loadListBoxFamilia();
        uiHomeProducto.getUiMantProducto().loadListBoxMarca();
        uiHomeProducto.getUiMantProducto().cleanForm();
        uiHomeProducto.getUiMantProducto().scrollPanel.refresh();
    }
    
    @Override
    public void showUIOper2() {
        // TODO Auto-generated method stub
        ProductoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeProducto.getContainer().showWidget(1);
        uiHomeProducto.getUiMantProducto().setModo(UiMantenimiento.MODOUPDATE);
        uiHomeProducto.getUiMantProducto().setBean(bean);
        uiHomeProducto.getUiMantProducto().loadFields();
        uiHomeProducto.getUiMantProducto().loadListBoxFamilia();
        uiHomeProducto.getUiMantProducto().loadListBoxMarca();
        uiHomeProducto.getUiMantProducto().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper3() {
        // TODO Auto-generated method stub
        ProductoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeProducto.getContainer().showWidget(1);
        uiHomeProducto.getUiMantProducto().setModo(UiMantenimiento.MODODELETE);
        uiHomeProducto.getUiMantProducto().setBean(bean);
        uiHomeProducto.getUiMantProducto().loadFields();
        uiHomeProducto.getUiMantProducto().loadListBoxFamilia();
        uiHomeProducto.getUiMantProducto().loadListBoxMarca();
        uiHomeProducto.getUiMantProducto().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }

    @Override
    public void showUIOper4() {
        // TODO Auto-generated method stub
        ProductoProxy bean=grid.getSelectionModel().getSelectedObject();
        if(bean!=null){
        uiHomeProducto.getContainer().showWidget(1);
        uiHomeProducto.getUiMantProducto().setModo(UiMantenimiento.MODODETALLE);
        uiHomeProducto.getUiMantProducto().setBean(bean);
        uiHomeProducto.getUiMantProducto().loadFields();
        uiHomeProducto.getUiMantProducto().loadListBoxFamilia();
        uiHomeProducto.getUiMantProducto().loadListBoxSubFamilia();
        uiHomeProducto.getUiMantProducto().loadListBoxMarca();
        uiHomeProducto.getUiMantProducto().scrollPanel.refresh();
        }else{
            //Window.alert("Seleccione un registro de la tabla");
        	Notification not=new Notification(Notification.ALERT,"Seleccione un registro de la tabla");
			not.showPopup();
        }
    }
}
