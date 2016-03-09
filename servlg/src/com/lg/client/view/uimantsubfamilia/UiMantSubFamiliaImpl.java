package com.lg.client.view.uimantsubfamilia;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.FamiliaProxy;
import com.lg.client.beanproxy.SubFamiliaProxy;
import com.lg.client.requestfactory.CntxMantFamilia;
import com.lg.client.requestfactory.CntxMantSubFamilia;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uisubfamilia.UiHomeSubFamilia;
import com.lg.client.view.uimantsubfamilia.UiMantSubFamilia;

public class UiMantSubFamiliaImpl  extends UiMantSubFamilia{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeSubFamilia uiHomeSubFamilia;

    public UiMantSubFamiliaImpl(UiHomeSubFamilia uiHomeSubFamilia) {        
        this.uiHomeSubFamilia = uiHomeSubFamilia;
        loadFamilia();
    }
    
    @Override
	public void loadFamilia() {
		// TODO Auto-generated method stub
    	CntxMantFamilia context = FACTORY.cntxMantFamilia();
        FACTORY.initialize(EVENTBUS);
        Request<List<FamiliaProxy>> request = context.listarFamilia();
        request.fire(new Receiver<List<FamiliaProxy>>() {

            @Override
            public void onSuccess(List<FamiliaProxy> response) {
            	lstFamilia.setData(response);
            	if(bean!=null){
            		lstFamilia.setSelectedItem(bean.getBeanFamilia().getDescripcion());
            	}
            }
        });
	}

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantSubFamilia context = FACTORY.cntxMantSubFamilia();
        FACTORY.initialize(EVENTBUS);
        SubFamiliaProxy bean = context.create(SubFamiliaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setIdSubFamilia(lstFamilia.getSelectedItem().getIdFamilia());        
        Request<Boolean> request = context.insertarSubFamilia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                cleanForm();
                //Window.alert("Insertado correctamente");
                Notification not=new Notification(Notification.INFORMATION,"Insertado correctamente");
                not.showPopup();
            	}else{
            		//Window.alert("Error al insertar");
            		Notification not=new Notification(Notification.ALERT,"Error al insertar");
                    not.showPopup();
            	}
            }
        });
    }

    @Override
    public void processActualizar() {
        Date fecha = new Date();
        CntxMantSubFamilia context = FACTORY.cntxMantSubFamilia();
        FACTORY.initialize(EVENTBUS);        
        SubFamiliaProxy bean = context.create(SubFamiliaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");        
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setCodeSubFamilia(this.bean.getIdSubFamilia());
        bean.setBeanFamilia(lstFamilia.getSelectedItem());   
        bean.setCodeFamilia(lstFamilia.getSelectedItem().getCodeFamilia());
        Request<Boolean> request = context.actualizarSubFamilia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiSubFamilia();
            	}else{
            		//Window.alert("Error al actualizar");
            		Notification not=new Notification(Notification.ALERT,"Error al actualizar");
                    not.showPopup();
            	}
            }
        });
    }

    @Override
    public void processEliminar() {
        Date fecha = new Date();
        CntxMantSubFamilia context = FACTORY.cntxMantSubFamilia();
        FACTORY.initialize(EVENTBUS);       
        SubFamiliaProxy bean = context.create(SubFamiliaProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeSubFamilia(this.bean.getIdSubFamilia());  
        bean.setAbreviatura(this.bean.getAbreviatura());
        Request<Boolean> request = context.eliminarSubFamilia(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiSubFamilia();
            	}else{
            		//Window.alert("Error al eliminar");
            		Notification not=new Notification(Notification.ALERT,"Error al eliminar");
                    not.showPopup();
            	}
            }
        });
    }

    @Override
    public void goToBack() {
    	uiHomeSubFamilia.getContainer().showWidget(0);        
    	uiHomeSubFamilia.getUiSubFamilia().loadTable();
    }
    
    @Override
    public void goToUiSubFamilia() {
        cleanForm();
        uiHomeSubFamilia.getContainer().showWidget(0);
        uiHomeSubFamilia.getUiSubFamilia().loadTable();
    }
}
