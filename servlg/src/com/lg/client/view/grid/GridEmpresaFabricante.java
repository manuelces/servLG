package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.EmpresaFabricanteProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridEmpresaFabricante extends DataGrid<EmpresaFabricanteProxy> {
	private List<EmpresaFabricanteProxy> data = new ArrayList<EmpresaFabricanteProxy>();
	private final SingleSelectionModel<EmpresaFabricanteProxy> selectionModel = new SingleSelectionModel<EmpresaFabricanteProxy>();
	private FilteredListDataProvider<EmpresaFabricanteProxy> dataProvider = new FilteredListDataProvider<EmpresaFabricanteProxy>(
			new IFilter<EmpresaFabricanteProxy>() {

				@Override
				public boolean isValid(EmpresaFabricanteProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdEmpresaFabricante()
								.toString()
								+ " "
								+ value.getDescripcion().toLowerCase()
								+ " "
								+ value.getRuc().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridEmpresaFabricante() {
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
		this.addColumn(descripcion, "DESCRIPCION");
		this.addColumn(ruc, "RUC");
	}

	private Column<EmpresaFabricanteProxy, String> descripcion = new Column<EmpresaFabricanteProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(EmpresaFabricanteProxy object) {
			return object.getDescripcion();
		}

	};

	private Column<EmpresaFabricanteProxy, String> ruc = new Column<EmpresaFabricanteProxy, String>(
			new TextCell()) {
		@Override
		public String getValue(EmpresaFabricanteProxy object) {
			return object.getRuc();
		}

	};

	public void setData(List<EmpresaFabricanteProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<EmpresaFabricanteProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<EmpresaFabricanteProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<EmpresaFabricanteProxy> getDataProvider() {
		return dataProvider;
	}
}
