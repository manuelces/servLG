package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.ProvinciaProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridProvincia extends DataGrid<ProvinciaProxy> {
	private List<ProvinciaProxy> data = new ArrayList<ProvinciaProxy>();
	private final SingleSelectionModel<ProvinciaProxy> selectionModel = new SingleSelectionModel<ProvinciaProxy>();
	private FilteredListDataProvider<ProvinciaProxy> dataProvider = new FilteredListDataProvider<ProvinciaProxy>(
			new IFilter<ProvinciaProxy>() {

				@Override
				public boolean isValid(ProvinciaProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdProvincia()
								.toString()+" "+value.getBeanDepartamento().getDescripcion().toLowerCase()
								+ " "+value.getCodigo().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridProvincia() {
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
		this.addColumn(pais, "PAIS");
		this.addColumn(departamento, "DEPARTAMENTO");
		this.addColumn(codigo, "CODIGO");
		this.addColumn(descripcion, "DESCRIPCION");
	}
	
	private Column<ProvinciaProxy, String> pais = new Column<ProvinciaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProvinciaProxy object) {
			return object.getDescPais();
		}
	};
	
	private Column<ProvinciaProxy, String> departamento = new Column<ProvinciaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProvinciaProxy object) {
			return object.getBeanDepartamento().getDescripcion();
		}
	};

	private Column<ProvinciaProxy, String> codigo = new Column<ProvinciaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProvinciaProxy object) {
			return object.getCodigo();
		}
	};

	private Column<ProvinciaProxy, String> descripcion = new Column<ProvinciaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProvinciaProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<ProvinciaProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<ProvinciaProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<ProvinciaProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ProvinciaProxy> getDataProvider() {
		return dataProvider;
	}
}
