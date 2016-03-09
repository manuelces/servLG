package com.lg.client.view.uisubelectro;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uisubelectro.UiSubElectroImpl;
import com.lg.client.view.uimantsubelectro.UiMantSubElectroImpl;

public class UiHomeSubElectro  extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiSubElectroImpl uiSubElectro;
	private UiMantSubElectroImpl uiMantSubElectro;

	public UiHomeSubElectro() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiSubElectro = new UiSubElectroImpl(this);
		uiMantSubElectro = new UiMantSubElectroImpl(this);
		container.add(uiSubElectro);
		container.add(uiMantSubElectro);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiSubElectroImpl getUiSubElectro() {
		return uiSubElectro;
	}

	public UiMantSubElectroImpl getUiMantSubElectro() {
		return uiMantSubElectro;
	}
}
