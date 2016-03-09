package com.lg.client.view.uimarca;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantmarca.UiMantMarcaImpl;

public class UiHomeMarca extends Composite {

	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiMarcaImpl uiMarca;
	private UiMantMarcaImpl uiMantMarca;

	public UiHomeMarca() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiMarca = new UiMarcaImpl(this);
		uiMantMarca = new UiMantMarcaImpl(this);
		container.add(uiMarca);
		container.add(uiMantMarca);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiMarcaImpl getUiMarca() {
		return uiMarca;
	}

	public UiMantMarcaImpl getUiMantMarca() {
		return uiMantMarca;
	}
}
