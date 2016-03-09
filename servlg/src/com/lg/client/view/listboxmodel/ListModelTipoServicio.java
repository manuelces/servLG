package com.lg.client.view.listboxmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.lg.client.beanproxy.TipoServicioProxy;

public class ListModelTipoServicio extends ListBox {

	private TipoServicioProxy selectedItem;
	private ArrayList<TipoServicioProxy> data = new ArrayList<TipoServicioProxy>();

	public ListModelTipoServicio() {
	}

	public ListModelTipoServicio(ArrayList<TipoServicioProxy> datos) {
		data = datos;
	}

	public void setData(List<TipoServicioProxy> datos) {
		if (data.isEmpty()) {
			data.addAll(datos);
		} else {
			clear();
			data.addAll(datos);
		}
		for (int i = 0; i < data.size(); i++) {
			insertItem(data.get(i).getDescripcion(), data.get(i)
					.getIdTipoServicio().toString(), i);
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

	public TipoServicioProxy getElement(int index)
			throws ArrayIndexOutOfBoundsException {
		try {
			return data.get(index);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw ex;
		}
	}

	public TipoServicioProxy getElement(String codigo) {
		Iterator<TipoServicioProxy> i = data.iterator();
		while (i.hasNext()) {
			TipoServicioProxy bean = (TipoServicioProxy) i.next();
			if (bean.getIdTipoServicio().toString().equals(codigo)) {
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
				TipoServicioProxy bean = data.get(i);
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

	public TipoServicioProxy getSelectedItem() {
		selectedItem = data.get(getSelectElement().getSelectedIndex());
		return selectedItem;
	}

}
