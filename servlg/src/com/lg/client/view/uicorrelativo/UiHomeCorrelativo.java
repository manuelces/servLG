package com.lg.client.view.uicorrelativo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantcorrelativo.UiMantCorrelativoImpl;

public class UiHomeCorrelativo extends Composite {
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiCorrelativoImpl uiCorrelativo;
	private UiMantCorrelativoImpl uiMantCorrelativo;

	public UiHomeCorrelativo() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiCorrelativo = new UiCorrelativoImpl(this);
		uiMantCorrelativo = new UiMantCorrelativoImpl(this);
		container.add(uiCorrelativo);
		container.add(uiMantCorrelativo);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiCorrelativoImpl getUiCorrelativo() {
		return uiCorrelativo;
	}

	public UiMantCorrelativoImpl getUiMantCorrelativo() {
		return uiMantCorrelativo;
	}
}
