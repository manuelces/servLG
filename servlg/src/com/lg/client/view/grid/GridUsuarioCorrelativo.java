package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.UsuarioCorrelativoProxy;
import com.lg.client.resource.MyResource;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridUsuarioCorrelativo extends DataGrid<UsuarioCorrelativoProxy> {
	private List<UsuarioCorrelativoProxy> data = new ArrayList<UsuarioCorrelativoProxy>();
	private final SingleSelectionModel<UsuarioCorrelativoProxy> selectionModel = new SingleSelectionModel<UsuarioCorrelativoProxy>();
	private FilteredListDataProvider<UsuarioCorrelativoProxy> dataProvider = new FilteredListDataProvider<UsuarioCorrelativoProxy>(
			new IFilter<UsuarioCorrelativoProxy>() {

				@Override
				public boolean isValid(UsuarioCorrelativoProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdUsuarioCorrelativo()
								.toString()
								+ " "
								+ value.getBeanUsuario().getLogin()
										.toLowerCase()
								+ " "
								+ value.getBeanCorrelativo().getCodeTipoDoc()
										.toLowerCase()
								+ " "
								+ " "
								+ value.getBeanCorrelativo().getSerie()
										.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridUsuarioCorrelativo() {
		initComponents();
	}

	private void initComponents() {
		this.setWidth("100%");
		this.setHeight("90%");
		initColumns();
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		this.setVisible(true);
		this.setSelectionModel(selectionModel);
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(SimplePager.TextLocation.CENTER,
				pagerResources, false, 0, true);
		pager.setDisplay(this);
		pager.setVisible(true);
	}

	private void initColumns() {
		this.addColumn(estado, "EST.");
		this.addColumn(tipodocumento, "T DOCUMENTO");
		this.addColumn(serie, "SERIE");
		this.addColumn(usuario, "USUARIO");
	}

	private Column<UsuarioCorrelativoProxy, ImageResource> estado = new Column<UsuarioCorrelativoProxy, ImageResource>(
			new ImageResourceCell()) {

		@Override
		public ImageResource getValue(UsuarioCorrelativoProxy object) {
			if (object.getEstadoActual() == 1) {
				return MyResource.INSTANCE.getImgGreen20();
			} else {
				return MyResource.INSTANCE.getImgGray20();
			}
		}

	};

	private Column<UsuarioCorrelativoProxy, String> tipodocumento = new Column<UsuarioCorrelativoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioCorrelativoProxy object) {
			return object.getBeanCorrelativo().getCodeTipoDoc();
		}
	};

	private Column<UsuarioCorrelativoProxy, String> serie = new Column<UsuarioCorrelativoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioCorrelativoProxy object) {
			return object.getBeanCorrelativo().getSerie();
		}
	};

	private Column<UsuarioCorrelativoProxy, String> usuario = new Column<UsuarioCorrelativoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioCorrelativoProxy object) {
			return object.getBeanUsuario().getLogin();
		}

	};

	public void setData(List<UsuarioCorrelativoProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<UsuarioCorrelativoProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<UsuarioCorrelativoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<UsuarioCorrelativoProxy> getDataProvider() {
		return dataProvider;
	}
}
