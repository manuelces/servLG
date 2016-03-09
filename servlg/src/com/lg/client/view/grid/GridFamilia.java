package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.FamiliaProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridFamilia extends DataGrid<FamiliaProxy> {
	private List<FamiliaProxy> data = new ArrayList<FamiliaProxy>();
	private final SingleSelectionModel<FamiliaProxy> selectionModel = new SingleSelectionModel<FamiliaProxy>();
	private FilteredListDataProvider<FamiliaProxy> dataProvider = new FilteredListDataProvider<FamiliaProxy>(
			new IFilter<FamiliaProxy>() {

				@Override
				public boolean isValid(FamiliaProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdFamilia()
								.toString()
								+ " "+value.getAbreviatura().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridFamilia() {
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

	private Column<FamiliaProxy, String> abreviatura = new Column<FamiliaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(FamiliaProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<FamiliaProxy, String> descripcion = new Column<FamiliaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(FamiliaProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<FamiliaProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<FamiliaProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<FamiliaProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<FamiliaProxy> getDataProvider() {
		return dataProvider;
	}
}
