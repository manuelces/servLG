package com.lg.client.view.uimenubar;

import java.util.ArrayList;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.lg.client.resource.MyResource;
import com.lg.client.util.TabClose;
import com.lg.client.view.uicliente.UiHomeCliente;
import com.lg.client.view.uicorrelativo.UiHomeCorrelativo;
import com.lg.client.view.uidepartamento.UiHomeDepartamento;
import com.lg.client.view.uidistrito.UiHomeDistrito;
import com.lg.client.view.uielectro.UiHomeElectro;
import com.lg.client.view.uiempresafabricante.UiHomeEmpresaFabricante;
import com.lg.client.view.uifamilia.UiHomeFamilia;
import com.lg.client.view.uimarca.UiHomeMarca;
import com.lg.client.view.uiordenservicio.UiHomeOrdenServicio;
import com.lg.client.view.uipais.UiHomePais;
import com.lg.client.view.uiproducto.UiHomeProducto;
import com.lg.client.view.uiprovincia.UiHomeProvincia;
import com.lg.client.view.uisesion.UiSesion;
import com.lg.client.view.uisubfamilia.UiHomeSubFamilia;
import com.lg.client.view.uitipodociden.UiHomeTipoDocIden;
import com.lg.client.view.uitipodocumento.UiHomeTipoDocumento;
import com.lg.client.view.uitiposervicio.UiHomeTipoServicio;
import com.lg.client.view.uitipotrabajador.UiHomeTipoTrabajador;
import com.lg.client.view.uitrabajador.UiHomeTrabajador;
import com.lg.client.view.uiusuario.UiHomeUsuario;
import com.lg.client.view.uiusuariocorrelativo.UiHomeUsuarioCorrelativo;

public class UiMenuBar extends Composite {
	private MenuBar menuBar;
	private MenuBar mbMantenimiento;
	private MenuBar mbRegistro;
	private MenuItem miTipoServicio;
	private MenuItem miTipoDocumento;
	private MenuItem miTipoDocIden;
	private MenuItem miFamilia;
	private MenuItem miSubFamilia;
	private MenuItem miProducto;
	private MenuItem miConcesionario;
	private MenuItem miMarca;
	private MenuItem miElectro;
	private MenuItem miTipoTrabajador;
	private MenuItem miTrabajador;
	private MenuItem miUsuario;
	private MenuItem miPais;
	private MenuItem miDepartamento;
	private MenuItem miProvincia;
	private MenuItem miDistrito;
	private MenuItem miCorrelativo;
	private MenuItem miUsuarioCorrelativo;
	private MenuItem miCliente;
	private MenuItem miOrdenServicio;

	public static ArrayList<TabClose> tabs = new ArrayList<TabClose>();

	public UiMenuBar() {
		initComponents();
		initStyle();
	}

	private void initComponents() {
		menuBar = new MenuBar();
		menuBar.setAnimationEnabled(true);
		menuBar.setAutoOpen(true);
		initMenuMain();
		initMenuMantenimiento();
		initMenuRegistro();
		this.initWidget(menuBar);
	}

	private void initStyle() {
		MyResource.INSTANCE.getStlUiMenuBar().ensureInjected();
	}

	private void initMenuMain() {
		mbMantenimiento = new MenuBar(true);
		menuBar.addItem("Mantenimientos", mbMantenimiento);
		mbRegistro = new MenuBar(true);
		menuBar.addItem("Registros", mbRegistro);
	}

	private void initMenuMantenimiento() {
		miTipoServicio = new MenuItem("Tipo servicio", showUiTipoServicio);
		mbMantenimiento.addItem(miTipoServicio);
		miTipoDocumento = new MenuItem("Tipo Documento", showUiTipoDocumento);
		mbMantenimiento.addItem(miTipoDocumento);
		miTipoDocIden = new MenuItem("Tipo Documento Identidad",
				showUiTipoDocIden);
		mbMantenimiento.addItem(miTipoDocIden);
		miFamilia = new MenuItem("Familia", showUiFamilia);
		miSubFamilia = new MenuItem("SubFamilia", showUiSubFamilia);
		miMarca = new MenuItem("Marca", showUiMarca);
		mbMantenimiento.addItem(miMarca);
		mbMantenimiento.addItem(miFamilia);
		mbMantenimiento.addItem(miSubFamilia);
		miProducto = new MenuItem("Producto", showUiProducto);
		mbMantenimiento.addItem(miProducto);
		miConcesionario = new MenuItem("Concesionario", showUiConcesionario);
		mbMantenimiento.addItem(miConcesionario);
		miElectro = new MenuItem("Electrodomestico", showUiElectro);
		mbMantenimiento.addItem(miElectro);
		miTipoTrabajador = new MenuItem("Tipo Trabajador", showUiTipoTrabajador);
		mbMantenimiento.addItem(miTipoTrabajador);
		miTrabajador = new MenuItem("Trabajador", showUiTrabajador);
		mbMantenimiento.addItem(miTrabajador);
		miUsuario = new MenuItem("Usuario", showUiUsuario);
		mbMantenimiento.addItem(miUsuario);
		miPais = new MenuItem("Pais", showUiPais);
		mbMantenimiento.addItem(miPais);
		miDepartamento = new MenuItem("Departamento", showUiDepartamento);
		mbMantenimiento.addItem(miDepartamento);
		miProvincia = new MenuItem("Provincia", showUiProvincia);
		mbMantenimiento.addItem(miProvincia);
		miDistrito = new MenuItem("Distrito", showUiDistrito);
		mbMantenimiento.addItem(miDistrito);
		miCorrelativo = new MenuItem("Correlativo", showUiCorrelativo);
		mbMantenimiento.addItem(miCorrelativo);
		miUsuarioCorrelativo = new MenuItem("Usuario Correlativo", showUiUsuarioCorrelativo);
		mbMantenimiento.addItem(miUsuarioCorrelativo);
		miCliente = new MenuItem("Cliente", showUiCliente);
		mbMantenimiento.addItem(miCliente);
	}
	
	private void initMenuRegistro(){
		miOrdenServicio= new MenuItem("Orden Servicio", showUiOrdenServicio);
		mbRegistro.addItem(miOrdenServicio);
	}

	Command showUiTipoServicio = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiTipoServicio == null) {
				UiSesion.uiTipoServicio = new UiHomeTipoServicio();
				TabClose tab = new TabClose(UiSesion.uiTipoServicio,
						"Tipo Servicio");
				UiSesion.tabPanel.add(UiSesion.uiTipoServicio, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoServicio);
			} else {
				TabClose tab = new TabClose(UiSesion.uiTipoServicio,
						"Tipo Servicio");
				UiSesion.tabPanel.add(UiSesion.uiTipoServicio, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoServicio);
			}
		}

	};

	Command showUiTipoDocumento = new Command() {
		@Override
		public void execute() {
			if (UiSesion.uiTipoDocumento == null) {
				UiSesion.uiTipoDocumento = new UiHomeTipoDocumento();
				TabClose tab = new TabClose(UiSesion.uiTipoDocumento,
						"Tipo Documento");
				UiSesion.tabPanel.add(UiSesion.uiTipoDocumento, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoDocumento);
			} else {
				TabClose tab = new TabClose(UiSesion.uiTipoDocumento,
						"Tipo Documento");
				UiSesion.tabPanel.add(UiSesion.uiTipoDocumento, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoDocumento);
			}
		}

	};

	Command showUiTipoDocIden = new Command() {
		@Override
		public void execute() {
			if (UiSesion.uiTipoDocIden == null) {
				UiSesion.uiTipoDocIden = new UiHomeTipoDocIden();
				TabClose tab = new TabClose(UiSesion.uiTipoDocIden,
						"Tipo Doc Identidad");
				UiSesion.tabPanel.add(UiSesion.uiTipoDocIden, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoDocIden);
			} else {
				TabClose tab = new TabClose(UiSesion.uiTipoDocIden,
						"Tipo Doc Identidad");
				UiSesion.tabPanel.add(UiSesion.uiTipoDocIden, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoDocIden);
			}
		}

	};

	Command showUiFamilia = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiFamilia == null) {
				UiSesion.uiFamilia = new UiHomeFamilia();
				TabClose tab = new TabClose(UiSesion.uiFamilia, "Familia");
				UiSesion.tabPanel.add(UiSesion.uiFamilia, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiFamilia);
			} else {
				TabClose tab = new TabClose(UiSesion.uiFamilia, "Familia");
				UiSesion.tabPanel.add(UiSesion.uiFamilia, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiFamilia);
			}
		}

	};

	Command showUiPais = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiPais == null) {
				UiSesion.uiPais = new UiHomePais();
				TabClose tab = new TabClose(UiSesion.uiPais, "Pais");
				UiSesion.tabPanel.add(UiSesion.uiPais, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiPais);
			} else {
				TabClose tab = new TabClose(UiSesion.uiPais, "Pais");
				UiSesion.tabPanel.add(UiSesion.uiPais, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiPais);
			}
		}

	};

	Command showUiSubFamilia = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiSubFamilia == null) {
				UiSesion.uiSubFamilia = new UiHomeSubFamilia();
				TabClose tab = new TabClose(UiSesion.uiSubFamilia, "SubFamilia");
				UiSesion.tabPanel.add(UiSesion.uiSubFamilia, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiSubFamilia);
			} else {
				TabClose tab = new TabClose(UiSesion.uiSubFamilia, "SubFamilia");
				UiSesion.tabPanel.add(UiSesion.uiSubFamilia, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiSubFamilia);
			}
		}

	};

	Command showUiProducto = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiProducto == null) {
				UiSesion.uiProducto = new UiHomeProducto();
				TabClose tab = new TabClose(UiSesion.uiProducto, "Producto");
				UiSesion.tabPanel.add(UiSesion.uiProducto, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiProducto);
			} else {
				TabClose tab = new TabClose(UiSesion.uiProducto, "Producto");
				UiSesion.tabPanel.add(UiSesion.uiProducto, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiProducto);
			}
		}

	};

	Command showUiConcesionario = new Command() {
		@Override
		public void execute() {
			if (UiSesion.uiConcesionario == null) {
				UiSesion.uiConcesionario = new UiHomeEmpresaFabricante();
				TabClose tab = new TabClose(UiSesion.uiConcesionario,
						"Concesionario");
				UiSesion.tabPanel.add(UiSesion.uiConcesionario, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiConcesionario);
			} else {
				TabClose tab = new TabClose(UiSesion.uiConcesionario,
						"Concesionario");
				UiSesion.tabPanel.add(UiSesion.uiConcesionario, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiConcesionario);
			}
		}

	};

	Command showUiMarca = new Command() {
		@Override
		public void execute() {
			if (UiSesion.uiMarca == null) {
				UiSesion.uiMarca = new UiHomeMarca();
				TabClose tab = new TabClose(UiSesion.uiMarca, "Marca");
				UiSesion.tabPanel.add(UiSesion.uiMarca, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiMarca);
			} else {
				TabClose tab = new TabClose(UiSesion.uiMarca, "Marca");
				UiSesion.tabPanel.add(UiSesion.uiMarca, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiMarca);
			}
		}

	};

	Command showUiElectro = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiElectro == null) {
				UiSesion.uiElectro = new UiHomeElectro();
				TabClose tab = new TabClose(UiSesion.uiElectro,
						"Electrodomestico");
				UiSesion.tabPanel.add(UiSesion.uiElectro, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiElectro);
			} else {
				TabClose tab = new TabClose(UiSesion.uiElectro,
						"Electrodomestico");
				UiSesion.tabPanel.add(UiSesion.uiElectro, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiElectro);
			}
		}

	};

	Command showUiTipoTrabajador = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiTipoTrabajador == null) {
				UiSesion.uiTipoTrabajador = new UiHomeTipoTrabajador();
				TabClose tab = new TabClose(UiSesion.uiTipoTrabajador,
						"Tipo Trabajador");
				UiSesion.tabPanel.add(UiSesion.uiTipoTrabajador, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoTrabajador);
			} else {
				TabClose tab = new TabClose(UiSesion.uiTipoTrabajador,
						"Tipo Trabajador");
				UiSesion.tabPanel.add(UiSesion.uiTipoTrabajador, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTipoTrabajador);
			}
		}

	};

	Command showUiTrabajador = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiTrabajador == null) {
				UiSesion.uiTrabajador = new UiHomeTrabajador();
				TabClose tab = new TabClose(UiSesion.uiTrabajador, "Trabajador");
				UiSesion.tabPanel.add(UiSesion.uiTrabajador, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTrabajador);
			} else {
				TabClose tab = new TabClose(UiSesion.uiTrabajador, "Trabajador");
				UiSesion.tabPanel.add(UiSesion.uiTrabajador, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiTrabajador);
			}
		}

	};

	Command showUiUsuario= new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiUsuario== null) {
				UiSesion.uiUsuario= new UiHomeUsuario();
				TabClose tab = new TabClose(UiSesion.uiUsuario, "Usuario");
				UiSesion.tabPanel.add(UiSesion.uiUsuario, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiUsuario);
			} else {
				TabClose tab = new TabClose(UiSesion.uiUsuario, "Usuario");
				UiSesion.tabPanel.add(UiSesion.uiUsuario, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiUsuario);
			}
		}

	};

	Command showUiDepartamento = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiDepartamento == null) {
				UiSesion.uiDepartamento = new UiHomeDepartamento();
				TabClose tab = new TabClose(UiSesion.uiDepartamento,
						"Departamento");
				UiSesion.tabPanel.add(UiSesion.uiDepartamento, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiDepartamento);
			} else {
				TabClose tab = new TabClose(UiSesion.uiDepartamento,
						"Departamento");
				UiSesion.tabPanel.add(UiSesion.uiDepartamento, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiDepartamento);
			}
		}

	};
	
	
	Command showUiProvincia = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiProvincia == null) {
				UiSesion.uiProvincia = new UiHomeProvincia();
				TabClose tab = new TabClose(UiSesion.uiProvincia,
						"Provincia");
				UiSesion.tabPanel.add(UiSesion.uiProvincia, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiProvincia);
			} else {
				TabClose tab = new TabClose(UiSesion.uiProvincia,
						"Provincia");
				UiSesion.tabPanel.add(UiSesion.uiProvincia, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiProvincia);
			}
		}

	};
	
	
	Command showUiDistrito = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiDistrito == null) {
				UiSesion.uiDistrito = new UiHomeDistrito();
				TabClose tab = new TabClose(UiSesion.uiDistrito,
						"Distrito");
				UiSesion.tabPanel.add(UiSesion.uiDistrito, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiDistrito);
			} else {
				TabClose tab = new TabClose(UiSesion.uiDistrito,
						"Distrito");
				UiSesion.tabPanel.add(UiSesion.uiDistrito, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiDistrito);
			}
		}

	};

	Command showUiCorrelativo = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiCorrelativo == null) {
				UiSesion.uiCorrelativo = new UiHomeCorrelativo();
				TabClose tab = new TabClose(UiSesion.uiCorrelativo,
						"Correlativo");
				UiSesion.tabPanel.add(UiSesion.uiCorrelativo, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiCorrelativo);
			} else {
				TabClose tab = new TabClose(UiSesion.uiCorrelativo,
						"Correlativo");
				UiSesion.tabPanel.add(UiSesion.uiCorrelativo, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiCorrelativo);
			}
		}

	};
	
	Command showUiUsuarioCorrelativo = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiUsuarioCorrelativo == null) {
				UiSesion.uiUsuarioCorrelativo = new UiHomeUsuarioCorrelativo();
				TabClose tab = new TabClose(UiSesion.uiUsuarioCorrelativo,
						"Usuario Correlativo");
				UiSesion.tabPanel.add(UiSesion.uiUsuarioCorrelativo, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiUsuarioCorrelativo);
			} else {
				TabClose tab = new TabClose(UiSesion.uiUsuarioCorrelativo,
						"Usuario Correlativo");
				UiSesion.tabPanel.add(UiSesion.uiUsuarioCorrelativo, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiUsuarioCorrelativo);
			}
		}

	};
	
	Command showUiCliente = new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiCliente == null) {
				UiSesion.uiCliente = new UiHomeCliente();
				TabClose tab = new TabClose(UiSesion.uiCliente,
						"Cliente");
				UiSesion.tabPanel.add(UiSesion.uiCliente, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiCliente);
			} else {
				TabClose tab = new TabClose(UiSesion.uiCliente,
						"Cliente");
				UiSesion.tabPanel.add(UiSesion.uiCliente, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiCliente);
			}
		}

	};
	
	Command showUiOrdenServicio= new Command() {

		@Override
		public void execute() {
			if (UiSesion.uiOrdenServicio == null) {
				UiSesion.uiOrdenServicio= new UiHomeOrdenServicio();
				TabClose tab = new TabClose(UiSesion.uiOrdenServicio,
						"Orden Servicio");
				UiSesion.tabPanel.add(UiSesion.uiOrdenServicio, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiOrdenServicio);
			} else {
				TabClose tab = new TabClose(UiSesion.uiOrdenServicio,
						"Orden Servicio");
				UiSesion.tabPanel.add(UiSesion.uiOrdenServicio, tab);
				tabs.add(tab);
				UiSesion.tabPanel.selectTab(UiSesion.uiOrdenServicio);
			}
		}

	};

}
