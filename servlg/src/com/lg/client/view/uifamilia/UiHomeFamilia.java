package com.lg.client.view.uifamilia;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantfamilia.UiMantFamiliaImpl;

public class UiHomeFamilia extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiFamiliaImpl uiFamilia;
	private UiMantFamiliaImpl uiMantFamilia;

	public UiHomeFamilia() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiFamilia = new UiFamiliaImpl(this);
		uiMantFamilia = new UiMantFamiliaImpl(this);
		container.add(uiFamilia);
		container.add(uiMantFamilia);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiFamiliaImpl getUiFamilia() {
		return uiFamilia;
	}

	public UiMantFamiliaImpl getUiMantFamilia() {
		return uiMantFamilia;
	}
}
