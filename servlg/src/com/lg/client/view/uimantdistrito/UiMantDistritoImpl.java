package com.lg.client.view.uimantdistrito;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.client.beanproxy.DistritoProxy;
import com.lg.client.requestfactory.CntxMantDepartamento;
import com.lg.client.requestfactory.CntxMantPais;
import com.lg.client.requestfactory.CntxMantProvincia;
import com.lg.client.requestfactory.CntxMantDistrito;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uidistrito.UiHomeDistrito;

public class UiMantDistritoImpl extends UiMantDistrito{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeDistrito uiHomeDistrito;

    public UiMantDistritoImpl(UiHomeDistrito uiHomeDistrito) {        
        this.uiHomeDistrito = uiHomeDistrito;
        loadPais();
    }
    
    @Override
	public void loadPais() {
		// TODO Auto-generated method stub
		CntxMantPais context = FACTORY.cntxMantPais();
        FACTORY.initialize(EVENTBUS);
        Request<List<PaisProxy>> request = context.listarPais();
        request.fire(new Receiver<List<PaisProxy>>() {

            @Override
            public void onSuccess(List<PaisProxy> response) {
            	if (!response.isEmpty()) {
            	lstPais.setData(response);
            	if(bean!=null){
            		lstPais.setSelectedItem(bean.getCodePais());
            	}
            	loadDepartamento();
            	}
            }
        });
	}
    
    @Override
	public void loadDepartamento() {
		// TODO Auto-generated method stub
    	CntxMantDepartamento context = FACTORY.cntxMantDepartamento();
        FACTORY.initialize(EVENTBUS);
        Request<List<DepartamentoProxy>> request = context.listarDepartamento(lstPais.getSelectedItem().getIdPais());
        request.fire(new Receiver<List<DepartamentoProxy>>() {

            @Override
            public void onSuccess(List<DepartamentoProxy> response) {
            	if (!response.isEmpty()) {
            	lstDepartamento.setData(response);
            	if(bean!=null){
            		lstDepartamento.setSelectedItem(bean.getCodeDepartamento());
            	}
            	loadProvincia();
            	}
            }
        });
	}	
    
    @Override
	public void loadProvincia() {
		// TODO Auto-generated method stub
    	CntxMantProvincia context = FACTORY.cntxMantProvincia();
        FACTORY.initialize(EVENTBUS);
        Request<List<ProvinciaProxy>> request = context.listarProvincia(lstDepartamento.getSelectedItem().getIdDepartamento());
        request.fire(new Receiver<List<ProvinciaProxy>>() {

            @Override
            public void onSuccess(List<ProvinciaProxy> response) {
            	lstProvincia.setData(response);
            	if(bean!=null){
            		lstProvincia.setSelectedItem(bean.getBeanProvincia().getIdProvincia());
            	}
            }
        });
	}

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantDistrito context = FACTORY.cntxMantDistrito();
        FACTORY.initialize(EVENTBUS);
        DistritoProxy bean = context.create(DistritoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setIdDistrito(lstProvincia.getSelectedItem().getIdProvincia());
        bean.setCodeProvincia(lstProvincia.getSelectedItem().getIdProvincia());
        bean.setBeanProvincia(lstProvincia.getSelectedItem());
        bean.setCodePais(lstPais.getSelectedItem().getIdPais());
        bean.setCodeDepartamento(lstDepartamento.getSelectedItem().getIdDepartamento());
        Request<Boolean> request = context.insertarDistrito(bean);
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
        CntxMantDistrito context = FACTORY.cntxMantDistrito();
        FACTORY.initialize(EVENTBUS);        
        DistritoProxy bean = context.create(DistritoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");        
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setCodigo(txtCodigo.getText().toUpperCase());
        bean.setCodeDistrito(this.bean.getIdDistrito());
        bean.setBeanProvincia(lstProvincia.getSelectedItem());   
        bean.setCodeProvincia(lstProvincia.getSelectedItem().getIdProvincia());
        bean.setCodePais(lstPais.getSelectedItem().getIdPais());
        bean.setCodeDepartamento(lstDepartamento.getSelectedItem().getIdDepartamento());
        Request<Boolean> request = context.actualizarDistrito(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                    not.showPopup();
                goToUiDistrito();
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
        CntxMantDistrito context = FACTORY.cntxMantDistrito();
        FACTORY.initialize(EVENTBUS);       
        DistritoProxy bean = context.create(DistritoProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeDistrito(this.bean.getIdDistrito());          
        Request<Boolean> request = context.eliminarDistrito(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                    not.showPopup();
                goToUiDistrito();
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
    	uiHomeDistrito.getContainer().showWidget(0);        
    	uiHomeDistrito.getUiDistrito().loadTable();
    }
    
    @Override
    public void goToUiDistrito() {
        cleanForm();
        uiHomeDistrito.getContainer().showWidget(0);
        uiHomeDistrito.getUiDistrito().loadTable();
    }
}
