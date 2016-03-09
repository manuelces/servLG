package com.lg.client.view.uiproducto;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantproducto.UiMantProductoImpl;

public class UiHomeProducto  extends Composite {
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiProductoImpl uiProducto;
	private UiMantProductoImpl uiMantProducto;
	
	public UiHomeProducto(){
		initComponents();
	}
	
	private void initComponents(){
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiProducto=new UiProductoImpl(this);
		uiMantProducto=new UiMantProductoImpl(this);
		container.add(uiProducto);
		container.add(uiMantProducto);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiProductoImpl getUiProducto() {
		return uiProducto;
	}

	public UiMantProductoImpl getUiMantProducto() {
		return uiMantProducto;
	}
	
	
}
