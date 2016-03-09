package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.TipoServicioProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridTipoServicio extends DataGrid<TipoServicioProxy> {
	private List<TipoServicioProxy> data = new ArrayList<TipoServicioProxy>();
	private final SingleSelectionModel<TipoServicioProxy> selectionModel = new SingleSelectionModel<TipoServicioProxy>();
	private FilteredListDataProvider<TipoServicioProxy> dataProvider = new FilteredListDataProvider<TipoServicioProxy>(
			new IFilter<TipoServicioProxy>() {

				@Override
				public boolean isValid(TipoServicioProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdTipoServicio().toString()
								+ " " + value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridTipoServicio() {
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
		this.addColumn(concesionario, "CONCESIONARIO");
		this.addColumn(abrev, "ABREV");
		this.addColumn(descripcion, "DESCRIPCION");
	}
	
	private Column<TipoServicioProxy, String> concesionario = new Column<TipoServicioProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoServicioProxy object) {
			return object.getBeanEmpresaFabricante().getDescripcion();
		}

	};
	
	private Column<TipoServicioProxy, String> abrev = new Column<TipoServicioProxy, String>(
			new TextCell()) {
		@Override
		public String getValue(TipoServicioProxy object) {
			return object.getAbreviatura();
		}
	};

	private Column<TipoServicioProxy, String> descripcion = new Column<TipoServicioProxy, String>(
			new TextCell()) {
		@Override
		public String getValue(TipoServicioProxy object) {
			return object.getDescripcion();
		}
	};

	public void setData(List<TipoServicioProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<TipoServicioProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoServicioProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoServicioProxy> getDataProvider() {
		return dataProvider;
	}
}
