package com.lg.client.model;

import com.google.gwt.user.client.ui.PasswordTextBox;
import com.lg.client.resource.MyResource;

public class MPasswordBox extends PasswordTextBox{
	
	public MPasswordBox(){
		initStyle();
	}
	
	private void initStyle(){
		this.getElement().setId("mpasswordbox");
		MyResource.INSTANCE.getStlMPasswordBox().ensureInjected();
	}
}
