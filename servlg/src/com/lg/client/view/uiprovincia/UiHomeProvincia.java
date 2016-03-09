package com.lg.client.view.uiprovincia;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantprovincia.UiMantProvinciaImpl;

public class UiHomeProvincia extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiProvinciaImpl uiProvincia;
	private UiMantProvinciaImpl uiMantProvincia;

	public UiHomeProvincia() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiProvincia = new UiProvinciaImpl(this);
		uiMantProvincia = new UiMantProvinciaImpl(this);
		container.add(uiProvincia);
		container.add(uiMantProvincia);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiProvinciaImpl getUiProvincia() {
		return uiProvincia;
	}

	public UiMantProvinciaImpl getUiMantProvincia() {
		return uiMantProvincia;
	}
}
