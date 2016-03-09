/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lg.client.view.uisearchaddcliente;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.lg.client.requestfactory.FactoryGestion;
import com.lg.client.util.PopupProgress;
import com.lg.client.view.uiordenservicio.UiHomeOrdenServicio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author SISTEMAS
 */
public class UISearchAddClienteImpl extends UISearchAddCliente {
    PopupProgress popup = new PopupProgress();
    private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
    private final EventBus EVENTBUS = new SimpleEventBus();
    private UiHomeOrdenServicio uiHomeOrdenServicio;

    public UISearchAddClienteImpl(UiHomeOrdenServicio uiHomeOrdenServicio) {
        this.uiHomeOrdenServicio = uiHomeOrdenServicio;
        //loadTable();
    }
    
    @Override
    public void goToBack() {
    	uiHomeOrdenServicio.getContainer().showWidget(0);
    }

    @Override
    public void processInsertar() {
        /*if (isValidData()) {
            Date fecha = new Date();
            ContextMantenimientoCliente context = FACTORY.contextMantenimientoCliente();
            FACTORY.initialize(EVENTBUS);
            String keyPublic = UISesion.keyPublic;
            ClienteProxy bean = context.create(ClienteProxy.class);
            bean.setVersion(fecha.getTime());
            bean.setTipoDocIden(formCliente.lstTipoDoc.getItemText(formCliente.lstTipoDoc.getSelectedIndex()));
            bean.setNombres(formCliente.txtNombres.getText());
            bean.setRuc(formCliente.txtDocumento.getText());
            bean.setIdPais(formCliente.lstPais.getSelectedItem().getIdPais());
            bean.setIdDepartamento(formCliente.lstDepartamento.getSelectedItem().getIdDepartamento());
            bean.setIdProvincia(formCliente.lstProvincia.getSelectedItem().getIdProvincia());
            bean.setIdDistrito(formCliente.lstDistrito.getSelectedItem().getIdDistrito());
            bean.setDireccion(formCliente.txtDireccion.getText());
            bean.setTelefono(formCliente.txtTelefono.getText());
            bean.setEmail(formCliente.txtEmail.getText());
            bean.setOperacion("I");
            Request<String> request = context.insertarCliente(keyPublic, bean);
            request.fire(new Receiver<String>() {

                @Override
                public void onSuccess(String response) {
                    String docCliente = formCliente.txtDocumento.getText();
                    cleanForm();
                    //Window.alert(response);
                    Notification not=new Notification(Notification.INFORMATION,response);
                    not.showPopup();
                    goToUIvdEntrada(docCliente);
                }

                @Override
                public void onFailure(ServerFailure error) {                    
                    //Window.alert(error.getMessage());
                    Notification not=new Notification(Notification.WARNING,error.getMessage());
                    not.showPopup();
                }

            });
        }*/
    }

    @Override
    public void goToUIvdEntrada(String docCliente) {
    	uiHomeOrdenServicio.getContainer().showWidget(0);
    	//uiHomeOrdenServicio.getUiSeachAddCliente().search(docCliente);
    }
}
