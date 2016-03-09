package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.TipoTrabajadorProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridTipoTrabajador extends DataGrid<TipoTrabajadorProxy> {
	private List<TipoTrabajadorProxy> data = new ArrayList<TipoTrabajadorProxy>();
	private final SingleSelectionModel<TipoTrabajadorProxy> selectionModel = new SingleSelectionModel<TipoTrabajadorProxy>();
	private FilteredListDataProvider<TipoTrabajadorProxy> dataProvider = new FilteredListDataProvider<TipoTrabajadorProxy>(
			new IFilter<TipoTrabajadorProxy>() {

				@Override
				public boolean isValid(TipoTrabajadorProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdTipoTrabajador().toString()
								+ " " + value.getAbreviatura().toLowerCase()
								+ " " + value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridTipoTrabajador() {
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
		this.addColumn(abreviatura, "ABREVIATURA");
		this.addColumn(descripcion, "DESCRIPCION");
	}

	private Column<TipoTrabajadorProxy, String> abreviatura = new Column<TipoTrabajadorProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoTrabajadorProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<TipoTrabajadorProxy, String> descripcion = new Column<TipoTrabajadorProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoTrabajadorProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<TipoTrabajadorProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<TipoTrabajadorProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoTrabajadorProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoTrabajadorProxy> getDataProvider() {
		return dataProvider;
	}
}
