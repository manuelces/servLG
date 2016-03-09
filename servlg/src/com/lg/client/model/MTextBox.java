package com.lg.client.model;

import com.google.gwt.user.client.ui.TextBox;
import com.lg.client.resource.MyResource;

public class MTextBox extends TextBox{
	
	public MTextBox(){
		initStyle();
	}
	
	private void initStyle(){
		this.getElement().setId("mtextbox");
		MyResource.INSTANCE.getStlMTextBox().ensureInjected();
	}
}
