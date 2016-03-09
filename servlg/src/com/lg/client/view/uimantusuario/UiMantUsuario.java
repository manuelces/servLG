package com.lg.client.view.uimantusuario;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.client.model.MPasswordBox;
import com.lg.client.model.MTextBox;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.uiutil.UIInputTrabajador;

public class UiMantUsuario extends UiMantenimiento implements
		InterUiMantUsuario, KeyUpHandler {
	protected MTextBox txtId;
	protected MTextBox txtLogin;
	protected MPasswordBox txtClave;
	protected UIInputTrabajador uiTrabajador;
	protected UsuarioProxy bean;
	protected TrabajadorProxy beanTrabajador;

	public UiMantUsuario() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtId = new MTextBox();
		txtLogin = new MTextBox();
		txtClave = new MPasswordBox();
		uiTrabajador = new UIInputTrabajador();
		this.addWidget("LOGIN (*)", txtLogin);
		this.addWidget("CLAVE (*)", txtClave);
		this.addWidget("TRABAJADOR (*)", uiTrabajador);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				reCalcularWindows();
			}
		});
	}

	private void reCalcularWindows() {
		int alto = Window.getClientHeight() - 220;
		this.scrollPanel.setHeight(alto + "px");
	}

	private void initListener() {		
		uiTrabajador.txtInputTrabajador.addKeyUpHandler(this);
		uiTrabajador.btnCombo.addClickHandler(clickHandler);
		uiTrabajador.txtInputTrabajador.addKeyUpHandler(this);
		uiTrabajador.gridTrabajador.getSelectionModel()
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						if (uiTrabajador.gridTrabajador.getSelectionModel()
								.getSelectedObject() != null) {
							beanTrabajador = uiTrabajador.gridTrabajador
									.getSelectionModel().getSelectedObject();
							uiTrabajador.txtInputTrabajador
									.setText(beanTrabajador.getPaterno() + " "
											+ beanTrabajador.getMaterno()
											+ ", " + beanTrabajador.getNombre());
							uiTrabajador.hidePopup();
							uiTrabajador.txtInputTrabajador.setFocus(true);
						}
					}
				});
	}

	private void initStyle() {
		uiTrabajador.setWidth("100%");
		// lstTipoTrabajador.setWidth("100%");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			/*
			 * txtId.setText(this.bean.getIdTrabajador().toString());
			 * txtPaterno.setText(this.bean.getPaterno());
			 * txtMaterno.setText(this.bean.getMaterno());
			 * txtNombre.setText(this.bean.getNombre());
			 * txtDni.setText(this.bean.getDni()); txtId.setEnabled(false);
			 * txtPaterno.setEnabled(true); txtMaterno.setEnabled(true);
			 * txtNombre.setEnabled(true); txtDni.setEnabled(true);
			 * txtPaterno.setFocus(true); txtPaterno.selectAll();
			 * lstTipoTrabajador.setEnabled(true);
			 * this.btnOperacion.setDisabled(false);
			 */
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)
				|| this.modo.equals("ACTIVAR")
				|| this.modo.equals("DESACTIVAR")) {
			txtId.setText(this.bean.getIdUsuario().toString());
			txtLogin.setText(this.bean.getLogin());
			txtClave.setText(this.bean.getClave());
			txtId.setEnabled(false);
			txtLogin.setEnabled(false);
			txtClave.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			txtId.setText(this.bean.getIdUsuario().toString());
			txtLogin.setText(this.bean.getLogin());
			txtClave.setText(this.bean.getClave());
			txtId.setEnabled(false);
			txtLogin.setEnabled(false);
			txtClave.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {
			txtId.setEnabled(false);
			txtLogin.setFocus(true);
			txtClave.setEnabled(true);
			txtClave.selectAll();
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
		txtLogin.setText(null);
		txtClave.setText(null);
	}

	@Override
	public void goToUiUsuario() {
		// TODO Auto-generated method stub

	}

	public void setBean(UsuarioProxy bean) {
		this.bean = bean;
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(txtClave)) {
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
				if (modo.equalsIgnoreCase(MODOINSERTAR)) {
					processInsertar();
				} else if (modo.equalsIgnoreCase(MODOUPDATE)) {
					processActualizar();
				} else if (modo.equalsIgnoreCase("ACTIVAR")) {
					processActivar();
				} else if (modo.equalsIgnoreCase("DESACTIVAR")) {
					processDesactivar();
				} else {
					Window.alert("Operacion no contemplada");
				}
			}
		}
		if (event.getSource().equals(uiTrabajador.gridTrabajador)) {
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER
					|| event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SPACE) {
				if (uiTrabajador.gridTrabajador.getDataProvider().hasFilter()) {
					uiTrabajador.gridTrabajador
							.getSelectionModel()
							.setSelected(
									uiTrabajador.gridTrabajador
											.getDataProvider().resulted
											.get(uiTrabajador.gridTrabajador
													.getKeyboardSelectedRow()),
									true);
				} else {
					uiTrabajador.gridTrabajador.getSelectionModel()
							.setSelected(
									uiTrabajador.gridTrabajador.getData().get(
											uiTrabajador.gridTrabajador
													.getKeyboardSelectedRow()),
									true);
				}
			} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
				uiTrabajador.hidePopup();
				uiTrabajador.txtInputTrabajador.setFocus(true);
			}
		}
	}
	
	ClickHandler clickHandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			uiTrabajador.showPopup();
		}
		
	};

	/*@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(uiTrabajador.btnCombo)) {
			uiTrabajador.showPopup();
		}
	}*/

	/*
	 * @Override public void loadTipoTrabajador() { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */

	@Override
	public void processActivar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processDesactivar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		if (event.getSource().equals(btnOperacion)) {
			if (modo.equalsIgnoreCase(MODOINSERTAR)) {
				processInsertar();
			} else if (modo.equalsIgnoreCase(MODOUPDATE)) {
				processActualizar();
			} else if (modo.equalsIgnoreCase(MODODELETE)) {
				processEliminar();
			} else if (modo.equalsIgnoreCase("ACTIVAR")) {
				processActivar();
			} else if (modo.equalsIgnoreCase("DESACTIVAR")) {
				processDesactivar();
			} else {
				Window.alert("Operacion no contemplada");
			}
		}
	}
}
