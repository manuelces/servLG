package com.lg.client.view.uisubfamilia;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uisubfamilia.UiSubFamiliaImpl;
import com.lg.client.view.uimantsubfamilia.UiMantSubFamiliaImpl;

public class UiHomeSubFamilia  extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiSubFamiliaImpl uiSubFamilia;
	private UiMantSubFamiliaImpl uiMantSubFamilia;

	public UiHomeSubFamilia() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiSubFamilia = new UiSubFamiliaImpl(this);
		uiMantSubFamilia = new UiMantSubFamiliaImpl(this);
		container.add(uiSubFamilia);
		container.add(uiMantSubFamilia);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiSubFamiliaImpl getUiSubFamilia() {
		return uiSubFamilia;
	}

	public UiMantSubFamiliaImpl getUiMantSubFamilia() {
		return uiMantSubFamilia;
	}
}
