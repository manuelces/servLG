package com.lg.client.view.uiordenservicio;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.lg.client.view.uimantordenservicio.UiMantOrdenServicioImpl;
import com.lg.client.view.uisearchaddcliente.UISearchAddClienteImpl;

public class UiHomeOrdenServicio extends Composite {
	private FlowPanel pnlContenedor;
	private DeckPanel container;
	private UiMantOrdenServicioImpl uiMantOrdenServicioImpl;
	private UISearchAddClienteImpl uiSeachAddCliente;

	public UiHomeOrdenServicio() {
		initComponents();
	}

	private void initComponents() {
		pnlContenedor = new FlowPanel();
		initWidget(pnlContenedor);
		container = new DeckPanel();
		container.setAnimationEnabled(true);
		pnlContenedor.add(container);
		// uiMantOrdenServicio = new UiMantOrdenServicio(this);
		uiMantOrdenServicioImpl = new UiMantOrdenServicioImpl(this);
		uiSeachAddCliente=new UISearchAddClienteImpl(this);
		container.add(uiMantOrdenServicioImpl);
		container.add(uiSeachAddCliente);
		container.showWidget(0);
	}

	public DeckPanel getContainer() {
		return container;
	}

	public UiMantOrdenServicioImpl getUiMantOrdenServicioImpl() {
		return uiMantOrdenServicioImpl;
	}
	
	public UISearchAddClienteImpl getUiSeachAddCliente() {
        return uiSeachAddCliente;
    }

}
