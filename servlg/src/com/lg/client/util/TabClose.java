package com.lg.client.util;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.lg.client.resource.MyResource;
import com.lg.client.view.uisesion.UiSesion;

public class TabClose extends Composite implements ClickHandler {

    private Label lblTitulo;
    private Button btnClose;
    private FlexTable pnlHor;
    private Widget ui;
    private String titulo;    
    
    public TabClose(Widget ui, String titulo) {
        this.ui = ui;
        this.titulo = titulo;
        initComponents();
        initListener();
        initStyle();
    }
    
    private void initComponents() {
        pnlHor = new FlexTable();
        lblTitulo = new Label(titulo);
        btnClose = new Button();
        pnlHor.setWidget(0, 0, lblTitulo);
        pnlHor.setWidget(0, 1,btnClose);
        initWidget(pnlHor);
    }
    
    private void initStyle() {   
        MyResource.INSTANCE.getStlTabClose().ensureInjected();		
        btnClose.addStyleName(MyResource.INSTANCE.getStlTabClose().btnClose());
        lblTitulo.addStyleName(MyResource.INSTANCE.getStlTabClose().lblTitulo());
    }
    
    private void initListener(){      
        btnClose.addClickHandler(this);
    }

    @Override
    public void onClick(ClickEvent event) {
        if(event.getSource().equals(btnClose)){
            UiSesion.tabPanel.remove(ui);            
        }
        
    }
              
    
}
