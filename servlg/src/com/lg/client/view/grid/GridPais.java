package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.PaisProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridPais extends DataGrid<PaisProxy> {
	private List<PaisProxy> data = new ArrayList<PaisProxy>();
	private final SingleSelectionModel<PaisProxy> selectionModel = new SingleSelectionModel<PaisProxy>();
	private FilteredListDataProvider<PaisProxy> dataProvider = new FilteredListDataProvider<PaisProxy>(
			new IFilter<PaisProxy>() {

				@Override
				public boolean isValid(PaisProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdPais()
								.toString()
								+ " "+value.getCodigo().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridPais() {
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
		this.addColumn(abreviatura, "CODIGO");
		this.addColumn(descripcion, "DESCRIPCION");
	}

	private Column<PaisProxy, String> abreviatura = new Column<PaisProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(PaisProxy object) {
			return object.getCodigo();
		}
	};

	private Column<PaisProxy, String> descripcion = new Column<PaisProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(PaisProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<PaisProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<PaisProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<PaisProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<PaisProxy> getDataProvider() {
		return dataProvider;
	}
}
