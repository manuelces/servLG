package com.lg.client.view.listboxmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.lg.client.beanproxy.TipoTrabajadorProxy;

public class ListModelTipoTrabajador extends ListBox {

	private TipoTrabajadorProxy selectedItem;
	private ArrayList<TipoTrabajadorProxy> data = new ArrayList<TipoTrabajadorProxy>();

	public ListModelTipoTrabajador() {
	}

	public ListModelTipoTrabajador(ArrayList<TipoTrabajadorProxy> datos) {
		data = datos;
	}

	public void setData(List<TipoTrabajadorProxy> datos) {
		if (data.isEmpty()) {
			data.addAll(datos);
		} else {
			clear();
			data.addAll(datos);
		}
		for (int i = 0; i < data.size(); i++) {
			insertItem(data.get(i).getDescripcion(), data.get(i)
					.getIdTipoTrabajador().toString(), i);
		}
		if (!data.isEmpty()) {
			selectedItem = data.get(0);
			setSelectedIndex(0);
		}
	}

	@Override
	public int getItemCount() {
		try {
			return data.size();
		} catch (NullPointerException ex) {
			return 0;
		}
	}

	@Override
	public void clear() {
		data.clear();
		getSelectElement().clear();
	}

	private SelectElement getSelectElement() {
		return getElement().cast();
	}

	public TipoTrabajadorProxy getElement(int index)
			throws ArrayIndexOutOfBoundsException {
		try {
			return data.get(index);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw ex;
		}
	}

	public TipoTrabajadorProxy getElement(String codigo) {
		Iterator<TipoTrabajadorProxy> i = data.iterator();
		while (i.hasNext()) {
			TipoTrabajadorProxy bean = (TipoTrabajadorProxy) i.next();
			if (bean.getIdTipoTrabajador().toString().equals(codigo)) {
				return bean;
			}
		}
		return null;
	}

	@Override
	public void setSelectedIndex(int index) {
		getSelectElement().setSelectedIndex(index);
		selectedItem = data.get(getSelectElement().getSelectedIndex());
	}

	public void setSelectedItem(String item) {
		if (item != null) {
			int index = -1;
			for (int i = 0; i < data.size(); i++) {
				TipoTrabajadorProxy bean = data.get(i);
				if (bean.getDescripcion().toString().equalsIgnoreCase(item)) {
					index = i;
				}
			}
			if (index >= 0) {
				getSelectElement().setSelectedIndex(index);
				selectedItem = data.get(getSelectElement().getSelectedIndex());
			}
		}
	}

	@Override
	public int getSelectedIndex() {
		return getSelectElement().getSelectedIndex();
	}

	public TipoTrabajadorProxy getSelectedItem() {
		selectedItem = data.get(getSelectElement().getSelectedIndex());
		return selectedItem;
	}

}
