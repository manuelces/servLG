package com.lg.client.view.uimantsubelectro;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ElectroProxy;
import com.lg.client.beanproxy.SubElectroProxy;
import com.lg.client.requestfactory.CntxMantElectro;
import com.lg.client.requestfactory.CntxMantSubElectro;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uisubelectro.UiHomeSubElectro;
import com.lg.client.view.uimantsubelectro.UiMantSubElectro;

public class UiMantSubElectroImpl  extends UiMantSubElectro{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeSubElectro uiHomeSubElectro;

    public UiMantSubElectroImpl(UiHomeSubElectro uiHomeSubElectro) {        
        this.uiHomeSubElectro = uiHomeSubElectro;
        loadElectro();
    }
    
    @Override
	public void loadElectro() {
		// TODO Auto-generated method stub
    	CntxMantElectro context = FACTORY.cntxMantElectro();
        FACTORY.initialize(EVENTBUS);
        Request<List<ElectroProxy>> request = context.listarElectro();
        request.fire(new Receiver<List<ElectroProxy>>() {

            @Override
            public void onSuccess(List<ElectroProxy> response) {
            	lstElectro.setData(response);
            	if(bean!=null){
            		lstElectro.setSelectedItem(bean.getBeanElectro().getDescripcion());
            	}
            }
        });
	}

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantSubElectro context = FACTORY.cntxMantSubElectro();
        FACTORY.initialize(EVENTBUS);
        SubElectroProxy bean = context.create(SubElectroProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setIdSubElectro(lstElectro.getSelectedItem().getIdElectro());        
        Request<Boolean> request = context.insertarSubElectro(bean);
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
        CntxMantSubElectro context = FACTORY.cntxMantSubElectro();
        FACTORY.initialize(EVENTBUS);        
        SubElectroProxy bean = context.create(SubElectroProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");        
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setCodeSubElectro(this.bean.getIdSubElectro());
        bean.setBeanElectro(lstElectro.getSelectedItem());   
        bean.setCodeElectro(lstElectro.getSelectedItem().getCodeElectro());
        Request<Boolean> request = context.actualizarSubElectro(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiSubElectro();
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
        CntxMantSubElectro context = FACTORY.cntxMantSubElectro();
        FACTORY.initialize(EVENTBUS);       
        SubElectroProxy bean = context.create(SubElectroProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeSubElectro(this.bean.getIdSubElectro());  
        bean.setAbreviatura(this.bean.getAbreviatura());
        Request<Boolean> request = context.eliminarSubElectro(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiSubElectro();
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
    	uiHomeSubElectro.getContainer().showWidget(0);        
    	uiHomeSubElectro.getUiSubElectro().loadTable();
    }
    
    @Override
    public void goToUiSubElectro() {
        cleanForm();
        uiHomeSubElectro.getContainer().showWidget(0);
        uiHomeSubElectro.getUiSubElectro().loadTable();
    }
}
