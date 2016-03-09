package com.lg.client.view.uimantusuariocorrelativo;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.lg.client.beanproxy.UsuarioCorrelativoProxy;
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.client.model.UiMantenimiento;
import com.lg.client.uiutil.UIInputUsuario;
import com.lg.client.view.listboxmodel.ListModelTipoDocumento;
import com.lg.client.view.listboxmodel.ListModelCorrelativo;

public class UiMantUsuarioCorrelativo extends UiMantenimiento implements
		InterUiMantUsuarioCorrelativo, ChangeHandler, BlurHandler ,KeyUpHandler {
	protected TextBox txtId;
	/*
	 * protected TextBox txtSerie; protected TextBox txtPreimpreso; protected
	 * TextBox txtNumInicial; protected TextBox txtNumFinal;
	 */
	protected ListModelTipoDocumento lstTipoDocumento;
	protected ListModelCorrelativo lstCorrelativo;
	protected UIInputUsuario uiUsuario;
	protected UsuarioCorrelativoProxy bean;
	protected UsuarioProxy beanUsuario;

	public UiMantUsuarioCorrelativo() {
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}

	private void initComponents() {
		txtId = new TextBox();
		/*
		 * txtSerie = new TextBox(); txtSerie.setMaxLength(4); txtPreimpreso =
		 * new TextBox(); txtPreimpreso.setMaxLength(10); txtNumInicial = new
		 * TextBox(); txtNumInicial.setMaxLength(10); txtNumFinal = new
		 * TextBox(); txtNumFinal.setMaxLength(10);
		 */
		lstTipoDocumento = new ListModelTipoDocumento();
		lstCorrelativo = new ListModelCorrelativo();
		uiUsuario = new UIInputUsuario();
		// this.addWidget("ID", txtId);
		this.addWidget("TIPO DOCUMENTO (*)", lstTipoDocumento);
		this.addWidget("CORRELATIVO (*)", lstCorrelativo);
		this.addWidget("USUARIO (*)", uiUsuario);
		/*
		 * this.addWidget("SERIE (*)", txtSerie); this.addWidget("INICIAL (*)",
		 * txtNumInicial); this.addWidget("FINAL (*)", txtNumFinal);
		 * this.addWidget("PREIMPRESO (*)", txtPreimpreso);
		 */
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
		/*
		 * txtSerie.addKeyUpHandler(this); txtSerie.addBlurHandler(this);
		 * txtSerie.addFocusHandler(this); txtPreimpreso.addKeyUpHandler(this);
		 * txtPreimpreso.addBlurHandler(this);
		 * txtPreimpreso.addFocusHandler(this);
		 * txtNumInicial.addKeyUpHandler(this);
		 * txtNumInicial.addBlurHandler(this);
		 * txtNumInicial.addFocusHandler(this);
		 * txtNumFinal.addKeyUpHandler(this); txtNumFinal.addBlurHandler(this);
		 * txtNumFinal.addFocusHandler(this);
		 */
		lstTipoDocumento.addChangeHandler(this);
		lstTipoDocumento.addBlurHandler(this);
		uiUsuario.txtInputUsuario.addKeyUpHandler(this);
		uiUsuario.btnCombo.addClickHandler(clickHandler);
		uiUsuario.txtInputUsuario.addKeyUpHandler(this);
		uiUsuario.gridUsuario.getSelectionModel()
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						if (uiUsuario.gridUsuario.getSelectionModel()
								.getSelectedObject() != null) {
							beanUsuario= uiUsuario.gridUsuario
									.getSelectionModel().getSelectedObject();
							uiUsuario.txtInputUsuario
									.setText(beanUsuario.getLogin());
							uiUsuario.hidePopup();
							uiUsuario.txtInputUsuario.setFocus(true);
						}
					}
				});
	}

	private void initStyle() {
		lstTipoDocumento.setWidth("100%");
		lstCorrelativo.setWidth("100%");
		uiUsuario.setWidth("100%");
		// txtSerie.getElement().setAttribute("type", "number");
		// txtSerie.getElement().setPropertyString("type", "number");
	}

	@Override
	public void loadFields() {
		// TODO Auto-generated method stub
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			txtId.setText(this.bean.getIdUsuarioCorrelativo().toString());
			/*
			 * txtSerie.setText(this.bean.getSerie());
			 * txtPreimpreso.setText(this.bean.getPreimpreso());
			 * txtNumInicial.setText(this.bean.getNumInicial());
			 * txtNumFinal.setText(this.bean.getNumFinal());
			 */
			txtId.setEnabled(false);
			/*
			 * txtSerie.setEnabled(true); txtPreimpreso.setEnabled(true);
			 * txtNumInicial.setEnabled(true); txtNumFinal.setEnabled(true);
			 * txtSerie.setFocus(true); txtSerie.selectAll();
			 */
			lstTipoDocumento.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODELETE)
				|| this.modo.equals("ACTIVAR")
				|| this.modo.equals("DESACTIVAR")) {
			txtId.setText(this.bean.getIdUsuarioCorrelativo().toString());
			/*
			 * txtSerie.setText(this.bean.getSerie());
			 * txtPreimpreso.setText(this.bean.getPreimpreso());
			 * txtNumInicial.setText(this.bean.getNumInicial());
			 * txtNumFinal.setText(this.bean.getNumFinal());
			 */
			txtId.setEnabled(false);
			/*
			 * txtSerie.setEnabled(false); txtPreimpreso.setEnabled(false);
			 * txtNumInicial.setEnabled(false); txtNumFinal.setEnabled(false);
			 */
			lstTipoDocumento.setEnabled(false);
			this.btnOperacion.setDisabled(false);
		} else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
			txtId.setText(this.bean.getIdUsuarioCorrelativo().toString());
			/*
			 * txtSerie.setText(this.bean.getSerie());
			 * txtPreimpreso.setText(this.bean.getPreimpreso());
			 * txtNumInicial.setText(this.bean.getNumInicial());
			 * txtNumFinal.setText(this.bean.getNumFinal());
			 */
			txtId.setEnabled(false);
			/*
			 * txtSerie.setEnabled(false); txtPreimpreso.setEnabled(false);
			 * txtNumInicial.setEnabled(false); txtNumFinal.setEnabled(false);
			 */
			lstTipoDocumento.setEnabled(false);
			this.btnOperacion.setDisabled(true);
		} else {
			txtId.setEnabled(false);
			/*
			 * txtSerie.setFocus(true); txtPreimpreso.setEnabled(true);
			 * txtNumInicial.setEnabled(true); txtNumFinal.setEnabled(true);
			 * txtSerie.selectAll(); txtPreimpreso.setEnabled(true);
			 */
			lstTipoDocumento.setEnabled(true);
			this.btnOperacion.setDisabled(false);
		}
	}

	@Override
	public void cleanForm() {
		// TODO Auto-generated method stub
		txtId.setText(null);
		/*
		 * txtSerie.setText(null); txtPreimpreso.setText(null);
		 * txtNumInicial.setText(null); txtNumFinal.setText(null);
		 */
	}

	@Override
	public void goToUiUsuarioCorrelativo() {
		// TODO Auto-generated method stub

	}

	public void setBean(UsuarioCorrelativoProxy bean) {
		this.bean = bean;
	}

	@Override
	public void loadTipoDocumento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCorrelativo() {
		// TODO Auto-generated method stub

	}

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
				Window.alert("Operaci�n no contemplada");
			}
		}
	}

	@Override
	public void onBlur(BlurEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(lstTipoDocumento)) {
			loadCorrelativo();
		}
	}
	
	ClickHandler clickHandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			uiUsuario.showPopup();
		}
		
	};

	@Override
	public void onChange(ChangeEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(lstTipoDocumento)) {
			loadCorrelativo();
		}
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(uiUsuario.gridUsuario)) {
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER
					|| event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SPACE) {
				if (uiUsuario.gridUsuario.getDataProvider().hasFilter()) {
					uiUsuario.gridUsuario
							.getSelectionModel()
							.setSelected(
									uiUsuario.gridUsuario
											.getDataProvider().resulted
											.get(uiUsuario.gridUsuario
													.getKeyboardSelectedRow()),
									true);
				} else {
					uiUsuario.gridUsuario.getSelectionModel()
							.setSelected(
									uiUsuario.gridUsuario.getData().get(
											uiUsuario.gridUsuario
													.getKeyboardSelectedRow()),
									true);
				}
			} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
				uiUsuario.hidePopup();
				uiUsuario.txtInputUsuario.setFocus(true);
			}
		}
	}

}
