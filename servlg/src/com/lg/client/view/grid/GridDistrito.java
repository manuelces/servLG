package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.DistritoProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridDistrito extends DataGrid<DistritoProxy> {
	private List<DistritoProxy> data = new ArrayList<DistritoProxy>();
	private final SingleSelectionModel<DistritoProxy> selectionModel = new SingleSelectionModel<DistritoProxy>();
	private FilteredListDataProvider<DistritoProxy> dataProvider = new FilteredListDataProvider<DistritoProxy>(
			new IFilter<DistritoProxy>() {

				@Override
				public boolean isValid(DistritoProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdDistrito()
								.toString()+" "+value.getBeanProvincia().getDescripcion().toLowerCase()
								+ " "+value.getCodigo().toLowerCase()+" "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridDistrito() {
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
		this.addColumn(pais, "PAIS");
		this.addColumn(departamento, "DEPARTAMENTO");
		this.addColumn(provincia, "PROVINCIA");
		this.addColumn(codigo, "CODIGO");
		this.addColumn(descripcion, "DESCRIPCION");
	}
	
	private Column<DistritoProxy, String> pais = new Column<DistritoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DistritoProxy object) {
			return object.getDescPais();
		}
	};
	
	private Column<DistritoProxy, String> departamento = new Column<DistritoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DistritoProxy object) {
			return object.getDescDepartamento();
		}
	};
	
	private Column<DistritoProxy, String> provincia = new Column<DistritoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DistritoProxy object) {
			return object.getBeanProvincia().getDescripcion();
		}
	};

	private Column<DistritoProxy, String> codigo = new Column<DistritoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DistritoProxy object) {
			return object.getCodigo();
		}
	};

	private Column<DistritoProxy, String> descripcion = new Column<DistritoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(DistritoProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<DistritoProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<DistritoProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<DistritoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<DistritoProxy> getDataProvider() {
		return dataProvider;
	}
}
