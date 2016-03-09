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
import com.lg.client.beanproxy.UsuarioProxy;
import com.lg.client.resource.MyResource;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridUsuario extends DataGrid<UsuarioProxy> {
	private List<UsuarioProxy> data = new ArrayList<UsuarioProxy>();
	private final SingleSelectionModel<UsuarioProxy> selectionModel = new SingleSelectionModel<UsuarioProxy>();
	private FilteredListDataProvider<UsuarioProxy> dataProvider = new FilteredListDataProvider<UsuarioProxy>(
			new IFilter<UsuarioProxy>() {

				@Override
				public boolean isValid(UsuarioProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdUsuario().toString()
								+ " "
								+ value.getBeanTrabajador().getPaterno()
										.toLowerCase()
								+ " "
								+ value.getBeanTrabajador().getMaterno()
										.toLowerCase()
								+ " "
								+ value.getBeanTrabajador().getNombre()
										.toLowerCase() + " "
								+ value.getLogin().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridUsuario() {
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
		this.addColumn(trabajador, "TRABAJADOR");
		this.addColumn(login, "LOGIN");
	}

	private Column<UsuarioProxy, ImageResource> estado = new Column<UsuarioProxy, ImageResource>(
			new ImageResourceCell()) {

		@Override
		public ImageResource getValue(UsuarioProxy object) {
			if (object.getEstadoActual() == 1) {
				return MyResource.INSTANCE.getImgGreen20();
			} else {
				return MyResource.INSTANCE.getImgGray20();
			}
		}

	};

	private Column<UsuarioProxy, String> trabajador = new Column<UsuarioProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioProxy object) {
			return object.getBeanTrabajador().getPaterno() + " "
					+ object.getBeanTrabajador().getMaterno() + ", "
					+ object.getBeanTrabajador().getNombre();
		}
	};

	private Column<UsuarioProxy, String> login = new Column<UsuarioProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioProxy object) {
			return object.getLogin();
		}

	};

	public void setData(List<UsuarioProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<UsuarioProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<UsuarioProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<UsuarioProxy> getDataProvider() {
		return dataProvider;
	}
}
