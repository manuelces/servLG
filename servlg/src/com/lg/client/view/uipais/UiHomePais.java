package com.lg.client.view.uipais;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantpais.UiMantPaisImpl;

public class UiHomePais  extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiPaisImpl uiPais;
	private UiMantPaisImpl uiMantPais;

	public UiHomePais() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiPais = new UiPaisImpl(this);
		uiMantPais = new UiMantPaisImpl(this);
		container.add(uiPais);
		container.add(uiMantPais);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiPaisImpl getUiPais() {
		return uiPais;
	}

	public UiMantPaisImpl getUiMantPais() {
		return uiMantPais;
	}
}
