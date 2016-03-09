package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.DepartamentoProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridDepartamento extends DataGrid<DepartamentoProxy> {
	private List<DepartamentoProxy> data = new ArrayList<DepartamentoProxy>();
	private final SingleSelectionModel<DepartamentoProxy> selectionModel = new SingleSelectionModel<DepartamentoProxy>();
	private FilteredListDataProvider<DepartamentoProxy> dataProvider = new FilteredListDataProvider<DepartamentoProxy>(
			new IFilter<DepartamentoProxy>() {

				@Override
				public boolean isValid(DepartamentoProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdDepartamento()
								.toString()+" "+value.getBeanPais().getDescripcion().toLowerCase()
								+ " "+value.getCodigo().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridDepartamento() {
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
		this.addColumn(Pais, "PAIS");
		this.addColumn(Codigo, "CODIGO");
		this.addColumn(descripcion, "DESCRIPCION");
	}
	
	private Column<DepartamentoProxy, String> Pais = new Column<DepartamentoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DepartamentoProxy object) {
			return object.getBeanPais().getDescripcion();
		}
	};

	private Column<DepartamentoProxy, String> Codigo = new Column<DepartamentoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DepartamentoProxy object) {
			return object.getCodigo();
		}
	};

	private Column<DepartamentoProxy, String> descripcion = new Column<DepartamentoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DepartamentoProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<DepartamentoProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<DepartamentoProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<DepartamentoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<DepartamentoProxy> getDataProvider() {
		return dataProvider;
	}
}
