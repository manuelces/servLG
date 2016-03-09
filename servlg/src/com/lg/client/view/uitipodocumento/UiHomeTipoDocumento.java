package com.lg.client.view.uitipodocumento;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimanttipodocumento.UiMantTipoDocumentoImpl;
import com.lg.client.view.uitipodocumento.UiTipoDocumentoImpl;

public class UiHomeTipoDocumento extends Composite {

	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiTipoDocumentoImpl uiTipoDocumento;
	private UiMantTipoDocumentoImpl uiMantTipoDocumento;

	public UiHomeTipoDocumento() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiTipoDocumento = new UiTipoDocumentoImpl(this);
		uiMantTipoDocumento = new UiMantTipoDocumentoImpl(this);
		container.add(uiTipoDocumento);
		container.add(uiMantTipoDocumento);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiTipoDocumentoImpl getUiTipoDocumento() {
		return uiTipoDocumento;
	}

	public UiMantTipoDocumentoImpl getUiMantTipoDocumento() {
		return uiMantTipoDocumento;
	}
}
