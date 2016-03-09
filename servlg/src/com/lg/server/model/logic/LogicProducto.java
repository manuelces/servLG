package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.Producto;
import com.lg.server.model.dao.DaoProducto;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicProducto {
	private PersistenceManager pm;

	public LogicProducto(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoProducto dao = new DaoProducto(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Object getBean(String id) throws UnknownException {
		DaoProducto dao = new DaoProducto(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}

	public Object getBean(Key id) throws UnknownException {
		DaoProducto dao = new DaoProducto(this.pm);
		return dao.getBean(id);
	}	

	public Collection<Producto> getListarBean() throws UnknownException {
		DaoProducto dao = new DaoProducto(this.pm);
		Collection<Producto> lista = dao.getListarBean();
		return lista;
	}
}
