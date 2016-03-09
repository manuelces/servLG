package com.lg.client.view.uitipotrabajador;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimanttipotrabajador.UiMantTipoTrabajadorImpl;

public class UiHomeTipoTrabajador extends Composite {
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiTipoTrabajadorImpl uiTipoTrabajador;
	private UiMantTipoTrabajadorImpl uiMantTipoTrabajador;

	public UiHomeTipoTrabajador() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiTipoTrabajador = new UiTipoTrabajadorImpl(this);
		uiMantTipoTrabajador = new UiMantTipoTrabajadorImpl(this);
		container.add(uiTipoTrabajador);
		container.add(uiMantTipoTrabajador);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiTipoTrabajadorImpl getUiTipoTrabajador() {
		return uiTipoTrabajador;
	}

	public UiMantTipoTrabajadorImpl getUiMantTipoTrabajador() {
		return uiMantTipoTrabajador;
	}
}
