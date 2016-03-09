package com.lg.client.view.uimanttiposervicio;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
import com.lg.client.beanproxy.TipoServicioProxy;
import com.lg.client.requestfactory.CntxMantEmpresaFabricante;
import com.lg.client.requestfactory.CntxMantTipoServicio;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.Notification;
import com.lg.client.view.uitiposervicio.UiHomeTipoServicio;

public class UiMantTipoServicioImpl extends UiMantTipoServicio{
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeTipoServicio uiHomeTipoServicio;

    public UiMantTipoServicioImpl(UiHomeTipoServicio uiHomeTipoServicio) {        
        this.uiHomeTipoServicio = uiHomeTipoServicio;
        loadEmpresaFabricante();
    }
    
    @Override
	public void loadEmpresaFabricante() {
		// TODO Auto-generated method stub
    	CntxMantEmpresaFabricante context = FACTORY.cntxMantEmpresaFabricante();
        FACTORY.initialize(EVENTBUS);
        Request<List<EmpresaFabricanteProxy>> request = context.listarEmpresaFabricante();
        request.fire(new Receiver<List<EmpresaFabricanteProxy>>() {

            @Override
            public void onSuccess(List<EmpresaFabricanteProxy> response) {
            	lstEmpresaFabricante.setData(response);
            	if(bean!=null){
            	lstEmpresaFabricante.setSelectedItem(bean.getBeanEmpresaFabricante().getDescripcion());
            	}
            }
        });
	}

    @Override
    public void processInsertar() {
        Date fecha = new Date();
        CntxMantTipoServicio context = FACTORY.cntxMantTipoServicio();
        FACTORY.initialize(EVENTBUS);
        TipoServicioProxy bean = context.create(TipoServicioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("I");
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        Request<Boolean> request = context.insertarTipoServicio(bean);
        bean.setIdTipoServicio(lstEmpresaFabricante.getSelectedItem().getIdEmpresaFabricante());
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
        CntxMantTipoServicio context = FACTORY.cntxMantTipoServicio();
        FACTORY.initialize(EVENTBUS);        
        TipoServicioProxy bean = context.create(TipoServicioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("A");        
        bean.setDescripcion(txtDescripcion.getText().toUpperCase());
        bean.setAbreviatura(txtAbreviatura.getText().toUpperCase());
        bean.setCodeTipoServicio(this.bean.getIdTipoServicio());
        bean.setBeanEmpresaFabricante(lstEmpresaFabricante.getSelectedItem());   
        bean.setCodeEmpresaFabricante(lstEmpresaFabricante.getSelectedItem().getCodeEmpresaFabricante());
        Request<Boolean> request = context.actualizarTipoServicio(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
            	if(response){
                //Window.alert("Actualizado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Actualizado correctamente");
    				not.showPopup();
                goToUiTipoServicio();
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
        CntxMantTipoServicio context = FACTORY.cntxMantTipoServicio();
        FACTORY.initialize(EVENTBUS);       
        TipoServicioProxy bean = context.create(TipoServicioProxy.class);
        bean.setVersion(fecha.getTime());
        bean.setOperacion("E");
        bean.setCodeTipoServicio(this.bean.getIdTipoServicio());  
        bean.setAbreviatura(this.bean.getAbreviatura());
        Request<Boolean> request = context.eliminarTipoServicio(bean);
        request.fire(new Receiver<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {    
            	if(response){
                //Window.alert("Eliminado correctamente");
            		Notification not=new Notification(Notification.INFORMATION,"Eliminado correctamente");
    				not.showPopup();
                goToUiTipoServicio();
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
    	uiHomeTipoServicio.getContainer().showWidget(0);        
    	uiHomeTipoServicio.getUiTipoServicio().loadTable();
    }
    
    @Override
    public void goToUiTipoServicio() {
        cleanForm();        
        uiHomeTipoServicio.getContainer().showWidget(0);
        uiHomeTipoServicio.getUiTipoServicio().loadTable();
    }
}
