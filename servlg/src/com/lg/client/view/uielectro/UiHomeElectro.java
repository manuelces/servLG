package com.lg.client.view.uielectro;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantelectro.UiMantElectroImpl;

public class UiHomeElectro extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiElectroImpl uiElectro;
	private UiMantElectroImpl uiMantElectro;

	public UiHomeElectro() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiElectro = new UiElectroImpl(this);
		uiMantElectro = new UiMantElectroImpl(this);
		container.add(uiElectro);
		container.add(uiMantElectro);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiElectroImpl getUiElectro() {
		return uiElectro;
	}

	public UiMantElectroImpl getUiMantElectro() {
		return uiMantElectro;
	}
}
