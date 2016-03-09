package com.lg.client.model;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.lg.client.resource.MyResource;

public class HeaderMenu extends FlexTable{
	
	public HeaderMenu(){
		initComponents();
		style();
	}
	
	private void initComponents(){
		FlexCellFormatter headCellFormatter = this.getFlexCellFormatter();		
		this.setWidth("100%");
		//this.setHeight("12mm");
		headCellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		headCellFormatter.setHorizontalAlignment(
		        0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		headCellFormatter.setHorizontalAlignment(
		        0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		headCellFormatter.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);		
		headCellFormatter.setWidth(0, 0, "32px");
		headCellFormatter.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_MIDDLE);
		headCellFormatter.setWidth(0, 2, "32px");
	}
	
	private void style() {
		MyResource.INSTANCE.getStlHeaderMenu().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlHeaderMenu().headerMenu());
	}
	
	public void setCenterWidget(Widget w) {
		this.setWidget(0, 1, w);		
	}
	
	public void setLeftWidget(Widget w) {
		this.setWidget(0, 0, w);
	}
	
	public void setRightWidget(Widget w) {
		this.setWidget(0, 2, w);
	}
	
	

}
