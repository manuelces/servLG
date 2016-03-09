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
import com.lg.client.beanproxy.TrabajadorProxy;
import com.lg.client.resource.MyResource;
import com.lg.client.util.FilteredListDataProvider;
import com.lg.client.util.IFilter;

public class GridTrabajador extends DataGrid<TrabajadorProxy> {
	private List<TrabajadorProxy> data = new ArrayList<TrabajadorProxy>();
	private final SingleSelectionModel<TrabajadorProxy> selectionModel = new SingleSelectionModel<TrabajadorProxy>();
	private FilteredListDataProvider<TrabajadorProxy> dataProvider = new FilteredListDataProvider<TrabajadorProxy>(
			new IFilter<TrabajadorProxy>() {

				@Override
				public boolean isValid(TrabajadorProxy value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getIdTrabajador().toString()
								+ " "
								+ value.getBeanTipoTrabajador().getDescripcion().toLowerCase() + " "
								+ value.getPaterno().toLowerCase() + " " + " "
								+ value.getMaterno().toLowerCase() + " " + " "
								+ value.getNombre().toLowerCase() + " "
								+ value.getDni().toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridTrabajador() {
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
		this.addColumn(tipotrabajador, "T TRABAJADOR");
		this.addColumn(trabajador, "TRABAJADOR");
		this.addColumn(dni, "DNI");
	}
	
	private Column<TrabajadorProxy, ImageResource> estado
    = new Column<TrabajadorProxy, ImageResource>(new ImageResourceCell()) {               

        @Override
        public ImageResource getValue(TrabajadorProxy object) {
            if(object.getEstadoActual()==1){
            return MyResource.INSTANCE.getImgGreen20();
            }else{
                return MyResource.INSTANCE.getImgGray20();
            }
        }

    };

	private Column<TrabajadorProxy, String> tipotrabajador = new Column<TrabajadorProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TrabajadorProxy object) {
			return object.getBeanTipoTrabajador().getDescripcion();
			//return "";
		}
	};

	private Column<TrabajadorProxy, String> trabajador = new Column<TrabajadorProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TrabajadorProxy object) {
			return object.getPaterno() + " " + object.getMaterno() + ", "
					+ object.getNombre();
		}
	};

	private Column<TrabajadorProxy, String> dni = new Column<TrabajadorProxy, String>(
			new TextCell()) {

		@Override
		public String getValue(TrabajadorProxy object) {
			return object.getDni();
		}

	};

	public void setData(List<TrabajadorProxy> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.refresh();
	}

	public List<TrabajadorProxy> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<TrabajadorProxy> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<TrabajadorProxy> getDataProvider() {
		return dataProvider;
	}
}
