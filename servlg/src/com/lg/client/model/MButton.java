package com.lg.client.model;

import com.google.gwt.user.client.ui.Button;
import com.lg.client.resource.MyResource;

public class MButton extends Button{
	
	public MButton(String text){
		super(text);
		initStyle();
	}
	
	private void initStyle(){
		this.getElement().setId("mbutton");
		MyResource.INSTANCE.getStlMButton().ensureInjected();
	}
	
	public void setDisabled(boolean value){
		this.setEnabled(!value);
	}
}
