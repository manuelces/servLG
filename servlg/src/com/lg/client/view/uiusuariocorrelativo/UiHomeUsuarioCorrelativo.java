package com.lg.client.view.uiusuariocorrelativo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantusuariocorrelativo.UiMantUsuarioCorrelativoImpl;

public class UiHomeUsuarioCorrelativo extends Composite {
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiUsuarioCorrelativoImpl uiUsuarioCorrelativo;
	private UiMantUsuarioCorrelativoImpl uiMantUsuarioCorrelativo;

	public UiHomeUsuarioCorrelativo() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiUsuarioCorrelativo = new UiUsuarioCorrelativoImpl(this);
		uiMantUsuarioCorrelativo = new UiMantUsuarioCorrelativoImpl(this);
		container.add(uiUsuarioCorrelativo);
		container.add(uiMantUsuarioCorrelativo);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiUsuarioCorrelativoImpl getUiUsuarioCorrelativo() {
		return uiUsuarioCorrelativo;
	}

	public UiMantUsuarioCorrelativoImpl getUiMantUsuarioCorrelativo() {
		return uiMantUsuarioCorrelativo;
	}
}
