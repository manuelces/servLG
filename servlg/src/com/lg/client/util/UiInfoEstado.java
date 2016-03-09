package com.lg.client.util;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class UiInfoEstado extends Composite{
    private FlexTable pnlContenedor;
    //public UIInfoTC infoTc;
    private HTML lblEstado;
    //private UIInfoUsuario infoUsuario;
    
    public UiInfoEstado(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlexTable();
        lblEstado = new HTML("Copyright &#64;2014");
        //infoUsuario=new UIInfoUsuario();
        //infoTc=new UIInfoTC();
        //pnlContenedor.setWidget(0, 0, infoUsuario);
        pnlContenedor.setWidget(0, 0, lblEstado);
        //pnlContenedor.setWidget(0, 2, infoTc);
        //pnlContenedor.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        pnlContenedor.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        //pnlContenedor.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
        this.initWidget(pnlContenedor);
    }
    
    private void initStyle(){
        pnlContenedor.setWidth("100%");
    }
}
