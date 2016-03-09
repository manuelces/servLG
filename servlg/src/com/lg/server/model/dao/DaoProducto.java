package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.lg.server.model.beans.Producto;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoProducto {
	private PersistenceManager pm;

	public DaoProducto(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Producto.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Producto> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Producto> lista = (Collection<Producto>) query
				.getListaBean(Producto.class);
		return lista;
	}
}
