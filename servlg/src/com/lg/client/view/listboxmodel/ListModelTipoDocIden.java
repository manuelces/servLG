package com.lg.client.view.listboxmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.lg.client.beanproxy.TipoDocIdenProxy;

public class ListModelTipoDocIden extends ListBox {

	private TipoDocIdenProxy selectedItem;
	private ArrayList<TipoDocIdenProxy> data = new ArrayList<TipoDocIdenProxy>();

	public ListModelTipoDocIden() {
	}

	public ListModelTipoDocIden(ArrayList<TipoDocIdenProxy> datos) {
		data = datos;
	}

	public void setData(List<TipoDocIdenProxy> datos) {
		if (data.isEmpty()) {
			data.addAll(datos);
		} else {
			clear();
			data.addAll(datos);
		}
		for (int i = 0; i < data.size(); i++) {
			insertItem(data.get(i).getDescripcion(), data.get(i).getIdTipoDoc()
					.toString(), i);
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

	public TipoDocIdenProxy getElement(int index)
			throws ArrayIndexOutOfBoundsException {
		try {
			return data.get(index);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw ex;
		}
	}

	public TipoDocIdenProxy getElement(String codigo) {
		Iterator<TipoDocIdenProxy> i = data.iterator();
		while (i.hasNext()) {
			TipoDocIdenProxy bean = (TipoDocIdenProxy) i.next();
			if (bean.getIdTipoDoc().toString().equals(codigo)) {
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

	public void setSelectedItem(String id) {
		if (id != null) {
			int index = -1;
			for (int i = 0; i < data.size(); i++) {
				TipoDocIdenProxy bean = data.get(i);
				if (bean.getIdTipoDoc().toString().equalsIgnoreCase(id)) {
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

	public TipoDocIdenProxy getSelectedItem() {
		selectedItem = data.get(getSelectElement().getSelectedIndex());
		return selectedItem;
	}

}
