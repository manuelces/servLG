package com.lg.client.view.uitipodociden;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimanttipodociden.UiMantTipoDocIdenImpl;
import com.lg.client.view.uitipodociden.UiTipoDocIdenImpl;

public class UiHomeTipoDocIden extends Composite {

	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiTipoDocIdenImpl uiTipoDocIden;
	private UiMantTipoDocIdenImpl uiMantTipoDocIden;

	public UiHomeTipoDocIden() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiTipoDocIden = new UiTipoDocIdenImpl(this);
		uiMantTipoDocIden = new UiMantTipoDocIdenImpl(this);
		container.add(uiTipoDocIden);
		container.add(uiMantTipoDocIden);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiTipoDocIdenImpl getUiTipoDocIden() {
		return uiTipoDocIden;
	}

	public UiMantTipoDocIdenImpl getUiMantTipoDocIden() {
		return uiMantTipoDocIden;
	}
}
