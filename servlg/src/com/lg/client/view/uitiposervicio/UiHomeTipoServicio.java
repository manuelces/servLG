package com.lg.client.view.uitiposervicio;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimanttiposervicio.UiMantTipoServicioImpl;

public class UiHomeTipoServicio extends Composite {

	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiTipoServicioImpl uiTipoServicio;
	private UiMantTipoServicioImpl uiMantTipoServicio;

	public UiHomeTipoServicio() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		uiTipoServicio = new UiTipoServicioImpl(this);
		uiMantTipoServicio = new UiMantTipoServicioImpl(this);
		container.add(uiTipoServicio);
		container.add(uiMantTipoServicio);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiTipoServicioImpl getUiTipoServicio() {
		return uiTipoServicio;
	}

	public UiMantTipoServicioImpl getUiMantTipoServicio() {
		return uiMantTipoServicio;
	}

}
