package com.lg.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.lg.client.util.Notification;
import com.lg.client.view.uisesion.UiSesionImpl;
//import com.lg.client.view.uitiposervicio.UiTipoServicio;


public class Servlg implements EntryPoint {

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

            @Override
            public void onUncaughtException(Throwable e) {
                //Throwable unwrapped=unwrap(e);
                //Window.alert(e.getMessage());
            	Notification not=new Notification(Notification.ALERT,e.getMessage());
            	not.showPopup();
            }
        });
		// TODO Auto-generated method stub
		Window.setMargin("0px");
		MGWT.applySettings(MGWTSettings.getAppSetting());
		RootPanel.get().add(new UiSesionImpl());
		//RootPanel.get().add(new UiTipoServicio());
	}
	
	public Throwable unwrap(Throwable e){
        if(e instanceof UmbrellaException){
            UmbrellaException ue=(UmbrellaException)e;
            if(ue.getCauses().size()==1){
                return unwrap(ue.getCauses().iterator().next());
            }
        }
        return e;
    }

}
