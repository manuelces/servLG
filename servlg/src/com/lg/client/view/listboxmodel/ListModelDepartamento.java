package com.lg.client.view.listboxmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;
import com.lg.client.beanproxy.DepartamentoProxy;

public class ListModelDepartamento extends ListBox {

	private DepartamentoProxy selectedItem;
	private ArrayList<DepartamentoProxy> data = new ArrayList<DepartamentoProxy>();

	public ListModelDepartamento() {
	}

	public ListModelDepartamento(ArrayList<DepartamentoProxy> datos) {
		data = datos;
	}

	public void setData(List<DepartamentoProxy> datos) {
		if (data.isEmpty()) {
			data.addAll(datos);
		} else {
			clear();
			data.addAll(datos);
		}
		for (int i = 0; i < data.size(); i++) {
			insertItem(data.get(i).getDescripcion(), data.get(i).getIdDepartamento()
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

	public DepartamentoProxy getElement(int index)
			throws ArrayIndexOutOfBoundsException {
		try {
			return data.get(index);
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw ex;
		}
	}

	public DepartamentoProxy getElement(String codigo) {
		Iterator<DepartamentoProxy> i = data.iterator();
		while (i.hasNext()) {
			DepartamentoProxy bean = (DepartamentoProxy) i.next();
			if (bean.getIdDepartamento().toString().equals(codigo)) {
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

	public void setSelectedItem(String code) {
		if (code != null) {
			int index = -1;
			for (int i = 0; i < data.size(); i++) {
				DepartamentoProxy bean = data.get(i);
				if (bean.getIdDepartamento().toString().equalsIgnoreCase(code)) {
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

	public DepartamentoProxy getSelectedItem() {
		selectedItem = data.get(getSelectElement().getSelectedIndex());
		return selectedItem;
	}

}
