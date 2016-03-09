package com.lg.client.view.listboxmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.lg.client.beanproxy.TipoDocumentoProxy;

public class ListModelTipoDocumento extends ListBox {

	private TipoDocumentoProxy selectedItem;
	private ArrayList<TipoDocumentoProxy> data = new ArrayList<TipoDocumentoProxy>();

	public ListModelTipoDocumento() {
	}

	public ListModelTipoDocumento(ArrayList<TipoDocumentoProxy> datos) {
		data = datos;
	}

	public void setData(List<TipoDocumentoProxy> datos) {
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

	public TipoDocumentoProxy getElement(int index)
			throws ArrayIndexOutOfBoundsException {
		try {
			return data.get(index);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw ex;
		}
	}

	public TipoDocumentoProxy getElement(String codigo) {
		Iterator<TipoDocumentoProxy> i = data.iterator();
		while (i.hasNext()) {
			TipoDocumentoProxy bean = (TipoDocumentoProxy) i.next();
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

	public void setSelectedItem(String item) {
		if (item != null) {
			int index = -1;
			for (int i = 0; i < data.size(); i++) {
				TipoDocumentoProxy bean = data.get(i);
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

	public TipoDocumentoProxy getSelectedItem() {
		selectedItem = data.get(getSelectElement().getSelectedIndex());
		return selectedItem;
	}

}
