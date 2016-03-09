package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.ElectroProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridElectro extends DataGrid<ElectroProxy> {
	private List<ElectroProxy> data = new ArrayList<ElectroProxy>();
	private final SingleSelectionModel<ElectroProxy> selectionModel = new SingleSelectionModel<ElectroProxy>();
	private FilteredListDataProvider<ElectroProxy> dataProvider = new FilteredListDataProvider<ElectroProxy>(
			new IFilter<ElectroProxy>() {

				@Override
				public boolean isValid(ElectroProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdElectro()
								.toString()
								+ " "+value.getAbreviatura().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridElectro() {
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

	private Column<ElectroProxy, String> abreviatura = new Column<ElectroProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ElectroProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<ElectroProxy, String> descripcion = new Column<ElectroProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ElectroProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<ElectroProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<ElectroProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<ElectroProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ElectroProxy> getDataProvider() {
		return dataProvider;
	}
}
