package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.SubFamiliaProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridSubFamilia extends DataGrid<SubFamiliaProxy> {
	private List<SubFamiliaProxy> data = new ArrayList<SubFamiliaProxy>();
	private final SingleSelectionModel<SubFamiliaProxy> selectionModel = new SingleSelectionModel<SubFamiliaProxy>();
	private FilteredListDataProvider<SubFamiliaProxy> dataProvider = new FilteredListDataProvider<SubFamiliaProxy>(
			new IFilter<SubFamiliaProxy>() {

				@Override
				public boolean isValid(SubFamiliaProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdSubFamilia()
								.toString()+" "+value.getBeanFamilia().getDescripcion().toLowerCase()
								+ " "+value.getAbreviatura().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridSubFamilia() {
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
		this.addColumn(familia, "FAMILIA");
		this.addColumn(abreviatura, "ABREVIATURA");
		this.addColumn(descripcion, "DESCRIPCION");
	}
	
	private Column<SubFamiliaProxy, String> familia = new Column<SubFamiliaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(SubFamiliaProxy object) {
			return object.getBeanFamilia().getDescripcion();
		}
	};

	private Column<SubFamiliaProxy, String> abreviatura = new Column<SubFamiliaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(SubFamiliaProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<SubFamiliaProxy, String> descripcion = new Column<SubFamiliaProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(SubFamiliaProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<SubFamiliaProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<SubFamiliaProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<SubFamiliaProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<SubFamiliaProxy> getDataProvider() {
		return dataProvider;
	}
}
