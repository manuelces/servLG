package com.lg.client.view.uiempresafabricante;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantempresafabricante.UiMantEmpresaFabricanteImpl;

public class UiHomeEmpresaFabricante extends Composite {

	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiEmpresaFabricanteImpl uiEmpresaFabricante;
	private UiMantEmpresaFabricanteImpl uiMantEmpresaFabricante;

	public UiHomeEmpresaFabricante() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiEmpresaFabricante = new UiEmpresaFabricanteImpl(this);
		uiMantEmpresaFabricante = new UiMantEmpresaFabricanteImpl(this);
		container.add(uiEmpresaFabricante);
		container.add(uiMantEmpresaFabricante);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiEmpresaFabricanteImpl getUiEmpresaFabricante() {
		return uiEmpresaFabricante;
	}

	public UiMantEmpresaFabricanteImpl getUiMantEmpresaFabricante() {
		return uiMantEmpresaFabricante;
	}

}
