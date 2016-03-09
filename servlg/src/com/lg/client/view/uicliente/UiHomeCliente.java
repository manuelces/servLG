package com.lg.client.view.uicliente;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantcliente.UiMantClienteImpl;

public class UiHomeCliente extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiClienteImpl uiCliente;
	private UiMantClienteImpl uiMantCliente;

	public UiHomeCliente() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiCliente = new UiClienteImpl(this);
		uiMantCliente = new UiMantClienteImpl(this);
		container.add(uiCliente);
		container.add(uiMantCliente);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiClienteImpl getUiCliente() {
		return uiCliente;
	}

	public UiMantClienteImpl getUiMantCliente() {
		return uiMantCliente;
	}
}
