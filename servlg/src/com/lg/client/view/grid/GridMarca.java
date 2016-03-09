package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.MarcaProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridMarca extends DataGrid<MarcaProxy> {
	private List<MarcaProxy> data = new ArrayList<MarcaProxy>();
	private final SingleSelectionModel<MarcaProxy> selectionModel = new SingleSelectionModel<MarcaProxy>();
	private FilteredListDataProvider<MarcaProxy> dataProvider = new FilteredListDataProvider<MarcaProxy>(
			new IFilter<MarcaProxy>() {

				@Override
				public boolean isValid(MarcaProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdMarca().toString() + " "
								+ value.getAbreviatura().toLowerCase() + " "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridMarca() {
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

	private Column<MarcaProxy, String> abreviatura = new Column<MarcaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(MarcaProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<MarcaProxy, String> descripcion = new Column<MarcaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(MarcaProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<MarcaProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<MarcaProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<MarcaProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<MarcaProxy> getDataProvider() {
		return dataProvider;
	}
}
