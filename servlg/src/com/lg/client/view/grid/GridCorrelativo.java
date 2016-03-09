package com.lg.client.view.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.SingleSelectionModel;
import com.lg.client.beanproxy.CorrelativoProxy;
import com.lg.client.resource.MyResource;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridCorrelativo extends DataGrid<CorrelativoProxy> {
	private List<CorrelativoProxy> data = new ArrayList<CorrelativoProxy>();
	private final SingleSelectionModel<CorrelativoProxy> selectionModel = new SingleSelectionModel<CorrelativoProxy>();
	private FilteredListDataProvider<CorrelativoProxy> dataProvider = new FilteredListDataProvider<CorrelativoProxy>(
			new IFilter<CorrelativoProxy>() {

				@Override
				public boolean isValid(CorrelativoProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdCorrelativo().toString()
								+ " "
								+ value.getBeanTipoDocumento().getDescripcion()
										.toLowerCase() + " "
								+ value.getSerie().toLowerCase() + " " + " "
								+ value.getPreimpreso().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridCorrelativo() {
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
		this.addColumn(estado, "EST.");
		this.addColumn(tipodocumento, "T DOCUMENTO");
		this.addColumn(serie, "SERIE");
		this.addColumn(preimpreso, "PREIMPRESO");
	}

	private Column<CorrelativoProxy, ImageResource> estado = new Column<CorrelativoProxy, ImageResource>(
			new ImageResourceCell()) {

		@Override
		public ImageResource getValue(CorrelativoProxy object) {
			if (object.getEstadoActual() == 1) {
				return MyResource.INSTANCE.getImgGreen20();
			} else {
				return MyResource.INSTANCE.getImgGray20();
			}
		}

	};

	private Column<CorrelativoProxy, String> tipodocumento = new Column<CorrelativoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(CorrelativoProxy object) {
			return object.getBeanTipoDocumento().getIdTipoDoc() + " - "
					+ object.getBeanTipoDocumento().getDescripcion();
		}
	};

	private Column<CorrelativoProxy, String> serie = new Column<CorrelativoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(CorrelativoProxy object) {
			return object.getSerie();
		}
	};

	private Column<CorrelativoProxy, String> preimpreso = new Column<CorrelativoProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(CorrelativoProxy object) {
			return object.getPreimpreso();
		}

	};

	public void setData(List<CorrelativoProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<CorrelativoProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<CorrelativoProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<CorrelativoProxy> getDataProvider() {
		return dataProvider;
	}
}
