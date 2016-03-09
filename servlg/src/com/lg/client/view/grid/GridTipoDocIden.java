package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.TipoDocIdenProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridTipoDocIden extends DataGrid<TipoDocIdenProxy> {

	private List<TipoDocIdenProxy> data = new ArrayList<TipoDocIdenProxy>();
	private final SingleSelectionModel<TipoDocIdenProxy> selectionModel = new SingleSelectionModel<TipoDocIdenProxy>();
	private FilteredListDataProvider<TipoDocIdenProxy> dataProvider = new FilteredListDataProvider<TipoDocIdenProxy>(
			new IFilter<TipoDocIdenProxy>() {

				@Override
				public boolean isValid(TipoDocIdenProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdTipoDoc().toString() + " "
								+ value.getAbrev().toLowerCase() + " "
								+ value.getDescripcion().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridTipoDocIden() {
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
		this.addColumn(id_tipo_doc, "ID");
		this.addColumn(descripcion, "DESCRIPCION");
		this.addColumn(abreviatura, "ABREVIATURA");
	}

	private Column<TipoDocIdenProxy, String> id_tipo_doc = new Column<TipoDocIdenProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoDocIdenProxy object) {
			return object.getIdTipoDoc();
		}
	};

	private Column<TipoDocIdenProxy, String> abreviatura = new Column<TipoDocIdenProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoDocIdenProxy object) {
			return object.getAbrev();
		}
	};

	private Column<TipoDocIdenProxy, String> descripcion = new Column<TipoDocIdenProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoDocIdenProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<TipoDocIdenProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<TipoDocIdenProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoDocIdenProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoDocIdenProxy> getDataProvider() {
		return dataProvider;
	}
}
