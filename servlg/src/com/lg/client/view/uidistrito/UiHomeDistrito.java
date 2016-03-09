package com.lg.client.view.uidistrito;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantdistrito.UiMantDistritoImpl;

public class UiHomeDistrito extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiDistritoImpl uiDistrito;
	private UiMantDistritoImpl uiMantDistrito;

	public UiHomeDistrito() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiDistrito = new UiDistritoImpl(this);
		uiMantDistrito = new UiMantDistritoImpl(this);
		container.add(uiDistrito);
		container.add(uiMantDistrito);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiDistritoImpl getUiDistrito() {
		return uiDistrito;
	}

	public UiMantDistritoImpl getUiMantDistrito() {
		return uiMantDistrito;
	}
}
