package com.lg.client.view.uiusuario;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uiusuario.UiUsuarioImpl;
import com.lg.client.view.uimantusuario.UiMantUsuarioImpl;

public class UiHomeUsuario extends Composite {
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiUsuarioImpl uiUsuario;
	private UiMantUsuarioImpl uiMantUsuario;

	public UiHomeUsuario() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiUsuario = new UiUsuarioImpl(this);
		uiMantUsuario = new UiMantUsuarioImpl(this);
		container.add(uiUsuario);
		container.add(uiMantUsuario);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiUsuarioImpl getUiUsuario() {
		return uiUsuario;
	}

	public UiMantUsuarioImpl getUiMantUsuario() {
		return uiMantUsuario;
	}
}
