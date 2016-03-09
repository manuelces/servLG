package com.lg.client.view.uidepartamento;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantdepartamento.UiMantDepartamentoImpl;

public class UiHomeDepartamento extends Composite{
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiDepartamentoImpl uiDepartamento;
	private UiMantDepartamentoImpl uiMantDepartamento;

	public UiHomeDepartamento() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiDepartamento = new UiDepartamentoImpl(this);
		uiMantDepartamento = new UiMantDepartamentoImpl(this);
		container.add(uiDepartamento);
		container.add(uiMantDepartamento);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiDepartamentoImpl getUiDepartamento() {
		return uiDepartamento;
	}

	public UiMantDepartamentoImpl getUiMantDepartamento() {
		return uiMantDepartamento;
	}
}
