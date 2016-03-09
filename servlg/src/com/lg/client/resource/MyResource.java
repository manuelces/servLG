package com.lg.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.lg.client.resource.cssresource.BorderLayoutCss;
import com.lg.client.resource.cssresource.HeaderMenuCss;
import com.lg.client.resource.cssresource.MButtonCss;
import com.lg.client.resource.cssresource.MPasswordBoxCss;
import com.lg.client.resource.cssresource.MTextBoxCss;
import com.lg.client.resource.cssresource.ModelCss;
import com.lg.client.resource.cssresource.NotificationCss;
import com.lg.client.resource.cssresource.TabCloseCss;
import com.lg.client.resource.cssresource.UiFormMantenimientoCss;
import com.lg.client.resource.cssresource.UiMenuBarCss;
import com.lg.client.resource.cssresource.UiScreenSesionCss;
import com.lg.client.resource.cssresource.UiSesionCss;

public interface MyResource extends ClientBundle{	
	public static final MyResource INSTANCE=GWT.create(MyResource.class);
	
	@Source("style/Notification.css")
	NotificationCss getStlNotification();
	
	@Source("style/MPasswordBox.css")
    MPasswordBoxCss getStlMPasswordBox();
    
	@Source("style/MButton.css")
    MButtonCss getStlMButton();
	
	@Source("style/MTextBox.css")
    MTextBoxCss getStlMTextBox();
	
    @Source("style/HeaderMenu.css")
    HeaderMenuCss getStlHeaderMenu();
    
    @Source("style/Model.css")
	ModelCss getStlModel();
    
    @Source("style/BorderLayout.css")
	BorderLayoutCss getStlBorderLayout();
    
    @Source("style/UiMantOrdenServicio.css")
	BorderLayoutCss getStlUiMantOrdenServicio();
    
    @Source("style/UiScreenSesion.css")
	UiScreenSesionCss getStlUiScreenSesion();
    
    @Source("style/TabClose.css")
	TabCloseCss getStlTabClose();
    
    @Source("style/UiMenuBar.css")
	UiMenuBarCss getStlUiMenuBar();
    
    @Source("style/UiSesion.css")
	UiSesionCss getStlUiSesion();
    
    @Source("style/UiFormMantenimiento.css")
	UiFormMantenimientoCss getStlUiFormMantenimiento();
    
    @Source("image/back32.png")
	ImageResource getImgBack32();
    
    @Source("image/cerrar16.png")
	ImageResource getImgCerrar16();
    
    @Source("image/gray20.png")
	ImageResource getImgGray20();
    
    @Source("image/green20.png")
	ImageResource getImgGreen20();
        
    @Source("image/abajo32.png")
	ImageResource getImgAbajo32();
    
    @Source("image/alert32.png")
	ImageResource getImgAlert32();

	@Source("image/warning32.png")
	ImageResource getImgWarning32();

	@Source("image/information32.png")
	ImageResource getImgInformation32();
	
	@Source("image/search32.png")
	ImageResource getImgSearch32();
}
