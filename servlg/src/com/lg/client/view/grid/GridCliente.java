package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.ClienteProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridCliente extends DataGrid<ClienteProxy> {
	private List<ClienteProxy> data = new ArrayList<ClienteProxy>();
	private final SingleSelectionModel<ClienteProxy> selectionModel = new SingleSelectionModel<ClienteProxy>();
	private FilteredListDataProvider<ClienteProxy> dataProvider = new FilteredListDataProvider<ClienteProxy>(
			new IFilter<ClienteProxy>() {

				@Override
				public boolean isValid(ClienteProxy value,
						String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdCliente()
								.toString();				
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridCliente() {
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
		this.addColumn(tipoDocIden, "TIPO DOC.");
		this.addColumn(numDocumento, "DOCUMENTO");
		this.addColumn(descripcion, "DESCRIPCION");
		this.addColumn(distrito, "DISTRITO");
	}
	
	private Column<ClienteProxy, String> distrito = new Column<ClienteProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ClienteProxy object) {
			return object.getBeanDistrito().getDescripcion();
		}
	};
	
	private Column<ClienteProxy, String> descripcion = new Column<ClienteProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ClienteProxy object) {
			return object.getDescripcion();
		}
	};

	private Column<ClienteProxy, String> tipoDocIden = new Column<ClienteProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ClienteProxy object) {
			return object.getBeanTipoDocIden().getDescripcion();
		}
	};

	private Column<ClienteProxy, String> numDocumento = new Column<ClienteProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(ClienteProxy object) {
			return object.getNumDocumento();
		}

	};

	public void setData(List<ClienteProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<ClienteProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<ClienteProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<ClienteProxy> getDataProvider() {
		return dataProvider;
	}
}

