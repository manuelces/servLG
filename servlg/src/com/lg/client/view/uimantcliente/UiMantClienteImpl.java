package com.lg.client.view.uimantcliente;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.ClienteProxy;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.client.beanproxy.DistritoProxy;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.client.beanproxy.TipoDocIdenProxy;
import com.lg.client.requestfactory.CntxMantCliente;
import com.lg.client.requestfactory.CntxMantDepartamento;
import com.lg.client.requestfactory.CntxMantDistrito;
import com.lg.client.requestfactory.CntxMantPais;
import com.lg.client.requestfactory.CntxMantProvincia;
import com.lg.client.requestfactory.CntxMantTipoDocIden;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uicliente.UiHomeCliente;

public class UiMantClienteImpl extends UiMantCliente{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeCliente uiHomeCliente;

    public UiMantClienteImpl(UiHomeCliente uiHomeCliente) {        
        this.uiHomeCliente = uiHomeCliente;
        loadTipoDocIden();
        loadPais();
    }
    
    @Override
	public void loadTipoDocIden() {
		// TODO Auto-generated method stub
    	CntxMantTipoDocIden context = FACTORY.cntxMantTipoDocIden();
		FACTORY.initialize(EVENTBUS);
		Request<List<TipoDocIdenProxy>> request = context.listarTipoDocIden();
		request.fire(new Receiver<List<TipoDocIdenProxy>>() {

			@Override
			public void onSuccess(List<TipoDocIdenProxy> response) {				
				lstTipoDocIden.setData(response);
			}
		});
	}

	@Override
	public void loadDistrito() {
		// TODO Auto-generated method stub
		CntxMantDistrito context = FACTORY.cntxMantDistrito();
        FACTORY.initialize(EVENTBUS);
        Request<List<DistritoProxy>> request = context.listarDistrito(lstProvincia.getSelectedItem().getIdProvincia());
        request.fire(new Receiver<List<DistritoProxy>>() {

            @Override
            public void onSuccess(List<DistritoProxy> response) {
            	lstDistrito.setData(response);
            	if(bean!=null){
            		lstDistrito.setSelectedItem(bean.getBeanDistrito().getIdDistrito());
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
            	if (!response.isEmpty()) {
            	lstProvincia.setData(response);
            	if(bean!=null){
            		lstProvincia.setSelectedItem(bean.getCodeProvincia());
            	}
            	loadDistrito();
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
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantCliente context = FACTORY.cntxMantCliente();
        FACTORY.initialize(EVENTBUS);
        ClienteProxy bean = context.create(ClienteProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");  
        bean.setIdCliente("");
        bean.setBeanTipoDocIden(lstTipoDocIden.getSelectedItem());
        bean.setNumDocumento(txtDocumento.getText().toUpperCase());
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setBeanDistrito(lstDistrito.getSelectedItem());
        bean.setDireccion(txtDireccion.getText().toUpperCase());
        bean.setCelular(txtCelular.getText().toUpperCase());
        bean.setTelefono(txtTelefono.getText().toUpperCase());
        bean.setCodeProvincia(lstProvincia.getSelectedItem().getIdProvincia());
        bean.setCodeDepartamento(lstDepartamento.getSelectedItem().getIdDepartamento());
        bean.setCodePais(lstPais.getSelectedItem().getIdPais());
        Request<Boolean> request = context.insertarCliente(bean);
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
        CntxMantCliente context = FACTORY.cntxMantCliente();
        FACTORY.initialize(EVENTBUS);        
        ClienteProxy bean = context.create(ClienteProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());               
        bean.setCodeCliente(this.bean.getIdCliente());
        Request<Boolean> request = context.actualizarCliente(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
                	not.showPopup();
                goToUiCliente();
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
        CntxMantCliente context = FACTORY.cntxMantCliente();
        FACTORY.initialize(EVENTBUS);       
        ClienteProxy bean = context.create(ClienteProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeCliente(this.bean.getIdCliente());  
        Request<Boolean> request = context.eliminarCliente(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
                	not.showPopup();
                goToUiCliente();
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
    	uiHomeCliente.getContainer().showWidget(0);        
    	uiHomeCliente.getUiCliente().loadTable();
    }
    
    @Override
    public void goToUiCliente() {
        cleanForm();
        uiHomeCliente.getContainer().showWidget(0);
        uiHomeCliente.getUiCliente().loadTable();
    }
}
