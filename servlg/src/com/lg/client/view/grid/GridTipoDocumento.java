package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.TipoDocumentoProxy;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridTipoDocumento extends DataGrid<TipoDocumentoProxy> {

	private List<TipoDocumentoProxy> data = new ArrayList<TipoDocumentoProxy>();
	private final SingleSelectionModel<TipoDocumentoProxy> selectionModel = new SingleSelectionModel<TipoDocumentoProxy>();
	private FilteredListDataProvider<TipoDocumentoProxy> dataProvider = new FilteredListDataProvider<TipoDocumentoProxy>(
			new IFilter<TipoDocumentoProxy>() {

				@Override
				public boolean isValid(TipoDocumentoProxy value, String filter) {
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

	public GridTipoDocumento() {
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

	private Column<TipoDocumentoProxy, String> id_tipo_doc = new Column<TipoDocumentoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoDocumentoProxy object) {
			return object.getIdTipoDoc();
		}
	};

	private Column<TipoDocumentoProxy, String> abreviatura = new Column<TipoDocumentoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoDocumentoProxy object) {
			return object.getAbrev();
		}
	};

	private Column<TipoDocumentoProxy, String> descripcion = new Column<TipoDocumentoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TipoDocumentoProxy object) {
			return object.getDescripcion();
		}

	};

	public void setData(List<TipoDocumentoProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<TipoDocumentoProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TipoDocumentoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TipoDocumentoProxy> getDataProvider() {
		return dataProvider;
	}
}
