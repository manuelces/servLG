package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.SubElectroProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridSubElectro extends DataGrid<SubElectroProxy> {
	private List<SubElectroProxy> data = new ArrayList<SubElectroProxy>();
	private final SingleSelectionModel<SubElectroProxy> selectionModel = new SingleSelectionModel<SubElectroProxy>();
	private FilteredListDataProvider<SubElectroProxy> dataProvider = new FilteredListDataProvider<SubElectroProxy>(
			new IFilter<SubElectroProxy>() {

				@Override
				public boolean isValid(SubElectroProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdSubElectro().toString()
								+ " "
								+ value.getBeanElectro().getDescripcion()
										.toLowerCase() + " "
								+ value.getAbreviatura().toLowerCase() + " "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridSubElectro() {
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
		this.addColumn(Electro, "Electro");
		this.addColumn(abreviatura, "ABREVIATURA");
		this.addColumn(descripcion, "DESCRIPCION");
	}

	private Column<SubElectroProxy, String> Electro = new Column<SubElectroProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(SubElectroProxy object) {
			return object.getBeanElectro().getDescripcion();
		}
	};

	private Column<SubElectroProxy, String> abreviatura = new Column<SubElectroProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(SubElectroProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<SubElectroProxy, String> descripcion = new Column<SubElectroProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(SubElectroProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<SubElectroProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<SubElectroProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<SubElectroProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<SubElectroProxy> getDataProvider() {
		return dataProvider;
	}
}
