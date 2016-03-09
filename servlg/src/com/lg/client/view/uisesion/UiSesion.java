package com.lg.client.view.uisesion;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.lg.client.model.UiScreenSesion;
import com.lg.client.resource.MyResource;
import com.lg.client.util.UiInfoEstado;
import com.lg.client.view.uicliente.UiHomeCliente;
import com.lg.client.view.uicorrelativo.UiHomeCorrelativo;
import com.lg.client.view.uidepartamento.UiHomeDepartamento;
import com.lg.client.view.uidistrito.UiHomeDistrito;
import com.lg.client.view.uielectro.UiHomeElectro;
import com.lg.client.view.uiempresafabricante.UiHomeEmpresaFabricante;
import com.lg.client.view.uifamilia.UiHomeFamilia;
import com.lg.client.view.uimarca.UiHomeMarca;
import com.lg.client.view.uimenubar.UiMenuBar;
import com.lg.client.view.uiordenservicio.UiHomeOrdenServicio;
import com.lg.client.view.uipais.UiHomePais;
import com.lg.client.view.uiproducto.UiHomeProducto;
import com.lg.client.view.uiprovincia.UiHomeProvincia;
import com.lg.client.view.uisubfamilia.UiHomeSubFamilia;
import com.lg.client.view.uitipodociden.UiHomeTipoDocIden;
import com.lg.client.view.uitipodocumento.UiHomeTipoDocumento;
import com.lg.client.view.uitiposervicio.UiHomeTipoServicio;
import com.lg.client.view.uitipotrabajador.UiHomeTipoTrabajador;
import com.lg.client.view.uitrabajador.UiHomeTrabajador;
import com.lg.client.view.uiusuario.UiHomeUsuario;
import com.lg.client.view.uiusuariocorrelativo.UiHomeUsuarioCorrelativo;

public class UiSesion extends UiScreenSesion implements InterUiSesion {

	private Label lblTitulo;
	protected UiInfoEstado infoEstado;
	private UiMenuBar mbSystem;
	public static TabLayoutPanel tabPanel;
	public static UiHomeTipoServicio uiTipoServicio;
	public static UiHomeTipoDocumento uiTipoDocumento;
	public static UiHomeTipoDocIden uiTipoDocIden;
	public static UiHomeFamilia uiFamilia;
	public static UiHomeSubFamilia uiSubFamilia;
	public static UiHomeProducto uiProducto;
	public static UiHomeEmpresaFabricante uiConcesionario;
	public static UiHomeMarca uiMarca;
	public static UiHomeElectro uiElectro;
	public static UiHomeTipoTrabajador uiTipoTrabajador;
	public static UiHomeTrabajador uiTrabajador;
	public static UiHomeUsuario uiUsuario;
	public static UiHomePais uiPais;
	public static UiHomeDepartamento uiDepartamento;
	public static UiHomeProvincia uiProvincia;
	public static UiHomeDistrito uiDistrito;
	public static UiHomeCorrelativo uiCorrelativo;
	public static UiHomeUsuarioCorrelativo uiUsuarioCorrelativo;
	public static UiHomeCliente uiCliente;
	public static UiHomeOrdenServicio uiOrdenServicio;

	public UiSesion() {
		initComponents();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		lblTitulo = new Label("SERVINORTE EIRL");
		this.setComponent(UiScreenSesion.TITULO, lblTitulo);
		infoEstado = new UiInfoEstado();
		this.setComponent(UiScreenSesion.ESTADO, infoEstado);
		mbSystem = new UiMenuBar();
		this.setComponent(UiScreenSesion.MENU, mbSystem);
		tabPanel = new TabLayoutPanel(2.5, Unit.EM);
		tabPanel.setAnimationDuration(1000);
		this.setComponent(UiScreenSesion.TAB, tabPanel);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}
		});
	}

	private void reCalcularWindows() {
		int alto = Window.getClientHeight() - 125;
		int ancho = Window.getClientWidth() - 5;
		tabPanel.setWidth(ancho + "px");
		tabPanel.setHeight(alto + "px");
	}

	private void initStyle() {
		lblTitulo.getElement().setId("lblTitulo");
		// lblEstado.getElement().setId("lblEstado");
		MyResource.INSTANCE.getStlUiSesion().ensureInjected();
	}

}
