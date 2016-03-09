package com.lg.client.view.uitrabajador;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uitrabajador.UiTrabajadorImpl;
import com.lg.client.view.uimanttrabajador.UiMantTrabajadorImpl;

public class UiHomeTrabajador  extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiTrabajadorImpl uiTrabajador;
	private UiMantTrabajadorImpl uiMantTrabajador;

	public UiHomeTrabajador() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiTrabajador = new UiTrabajadorImpl(this);
		uiMantTrabajador = new UiMantTrabajadorImpl(this);
		container.add(uiTrabajador);
		container.add(uiMantTrabajador);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiTrabajadorImpl getUiTrabajador() {
		return uiTrabajador;
	}

	public UiMantTrabajadorImpl getUiMantTrabajador() {
		return uiMantTrabajador;
	}
}
