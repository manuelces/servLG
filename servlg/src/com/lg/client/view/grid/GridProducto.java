package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.ProductoProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridProducto  extends DataGrid<ProductoProxy> {

	private List<ProductoProxy> data = new ArrayList<ProductoProxy>();
	private final SingleSelectionModel<ProductoProxy> selectionModel = new SingleSelectionModel<ProductoProxy>();
	private FilteredListDataProvider<ProductoProxy> dataProvider = new FilteredListDataProvider<ProductoProxy>(
			new IFilter<ProductoProxy>() {

				@Override
				public boolean isValid(ProductoProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdProducto().toString()
								+ " " + value.getDescripcion().toLowerCase()
								+ " " + value.getModelo().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridProducto() {
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
		//this.addColumn(id, "ID");
		this.addColumn(familia, "FAMILIA");
		this.addColumn(subFamilia, "SUBFAMILIA");
		this.addColumn(marca, "MARCA");
		this.addColumn(descripcion, "DESCRIPCION");
		this.addColumn(modelo, "MODELO");
	}

	/*private Column<ProductoProxy, String> id = new Column<ProductoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProductoProxy object) {
			return object.getIdProducto();
		}
	};*/
	
	private Column<ProductoProxy, String> marca = new Column<ProductoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProductoProxy object) {
			return object.getBeanMarca().getDescripcion();
		}

	};
	
	private Column<ProductoProxy, String> familia = new Column<ProductoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProductoProxy object) {
			return object.getDescFamilia();
		}

	};
	
	private Column<ProductoProxy, String> subFamilia = new Column<ProductoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProductoProxy object) {
			return object.getBeanSubFamilia().getBeanFamilia().getDescripcion();
		}

	};

	private Column<ProductoProxy, String> descripcion = new Column<ProductoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProductoProxy object) {
			return object.getDescripcion();
		}

	};

	private Column<ProductoProxy, String> modelo = new Column<ProductoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ProductoProxy object) {
			return object.getModelo();
		}

	};

	public void setData(List<ProductoProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<ProductoProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<ProductoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ProductoProxy> getDataProvider() {
		return dataProvider;
	}
}

