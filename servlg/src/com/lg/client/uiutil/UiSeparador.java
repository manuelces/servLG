package com.lg.client.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Label;

public class UiSeparador extends Label{

	public UiSeparador(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        this.setText("|");
    }
    
    private void initStyle(){
        this.getElement().getStyle().setMargin(5, Style.Unit.PX);
    }
    
    public Label isSpace(){
        this.setText("");
        initStyle();
        return this;
    }
}
