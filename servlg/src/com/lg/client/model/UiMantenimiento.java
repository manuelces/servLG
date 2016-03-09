package com.lg.client.model;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.lg.client.resource.MyResource;
import com.lg.client.util.Notification;
import com.lg.client.model.MButton;

public class UiMantenimiento extends Composite implements ClickHandler,TouchEndHandler{	
    public final static String MODOINSERTAR = "INSERTAR";
    public final static String MODOUPDATE = "ACTUALIZAR";
    public final static String MODODELETE = "ELIMINAR";
    public final static String MODODETALLE = "INFORMACION";
    private FlowPanel pnlContenedor;
    protected HeaderMenu header;
    protected Label lblCenter;
    private PushButton btnBack;
    public ScrollPanel scrollPanel;
    protected FlowPanel contenido;
    protected MButton btnOperacion;
    protected FlowPanel pnlForm;
    protected ContentForm contentForm;
    protected String modo=MODOINSERTAR;
    
    public UiMantenimiento() {
        initComponents();
        initListener();
        initStyle();
        reCalcularWindows();
    }
    
    private void initComponents() {
        header = new HeaderMenu();
        lblCenter = new Label("MODO - "+MODOINSERTAR);
        header.setCenterWidget(lblCenter);
        btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
        btnBack.setTitle("Volver Atras");
        header.setLeftWidget(btnBack);
        contenido = new FlowPanel();
        scrollPanel = new ScrollPanel();
        scrollPanel.setScrollingEnabledY(true);
        scrollPanel.setScrollingEnabledX(false);
        scrollPanel.setAutoHandleResize(true);
        scrollPanel.setWidget(contenido);        
        pnlForm = new FlowPanel();
        contenido.add(pnlForm);        
        contentForm = new ContentForm();        
        pnlForm.add(contentForm);
        btnOperacion = new MButton("ACEPTAR");
        //btnOperacion.setConfirm(true);
        contenido.add(btnOperacion);
        pnlContenedor = new FlowPanel();
        pnlContenedor.add(header);
        pnlContenedor.add(scrollPanel);
        this.initWidget(pnlContenedor);
        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                reCalcularWindows();
            }
        });
    }
    
    private void initListener(){
        btnOperacion.addTouchEndHandler(this);
        btnOperacion.addClickHandler(this);
        btnBack.addClickHandler(this);
    }
    
    private void initStyle() {
        MyResource.INSTANCE.getStlModel().ensureInjected();        
        btnBack.addStyleName(MyResource.INSTANCE.getStlModel().pushButton());
        contenido.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
        btnOperacion.getElement().getStyle().setMargin(0, Style.Unit.PX);
        pnlForm.getElement().getStyle().setPadding(10, Style.Unit.PX);
        contenido.getElement().getStyle().setDisplay(Style.Display.BLOCK);            
    }
    
    public void addWidget(String name,Widget element){
    	//FormEntry wEntry=new FormEntry(name,element);
    	//wEntry.widgetContainer.getElement().getStyle().setBackgroundColor("white");
        contentForm.addWidget(name, element);
    }
    
    private void reCalcularWindows(){
        //int ancho = Window.getClientWidth() - 30;
        //btnOperacion.setWidth(ancho + "px");
    }

    @Override
    public void onClick(ClickEvent event) {
        if(event.getSource().equals(btnBack)){
            goToBack();
        }else if (event.getSource().equals(btnOperacion)) {
            if (modo.equalsIgnoreCase(MODOINSERTAR)) {                
                processInsertar();
            } else if (modo.equalsIgnoreCase(MODOUPDATE)) {                
                processActualizar();
            } else if (modo.equalsIgnoreCase(MODODELETE)) {
                processEliminar();
            } else {
                //Window.alert("Operaci�n no contemplada");
            	Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
            	not.showPopup();
            }
        }
    }
    
    public void goToBack(){
        
    }
    
    public void processInsertar() {
        // TODO Auto-generated method stub	        
    }

    public void processActualizar() {
        // TODO Auto-generated method stub		
    }

    public void processEliminar() {
        // TODO Auto-generated method stub		
    }
    
    public void setButtonAcept(String operacion){
        btnOperacion.setText(operacion);
    }

    public void setModo(String modo) {
        lblCenter.setText("MODO - "+modo);
        btnOperacion.setText(modo);
        this.modo = modo;
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
        if (event.getSource().equals(btnOperacion)) {
            if (modo.equalsIgnoreCase(MODOINSERTAR)) {                
                processInsertar();
            } else if (modo.equalsIgnoreCase(MODOUPDATE)) {                
                processActualizar();
            } else if (modo.equalsIgnoreCase(MODODELETE)) {
                processEliminar();
            } else {
                //Window.alert("Operaci�n no contemplada");
            	Notification not=new Notification(Notification.ALERT,"Operacion no contemplada");
            	not.showPopup();
            }
        }
    }

       
       
}
