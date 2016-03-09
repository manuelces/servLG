package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.lg.server.model.beans.Familia;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoFamilia {
	private PersistenceManager pm;

	public DaoFamilia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Familia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Familia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Familia> lista = (Collection<Familia>) query
				.getListaBean(Familia.class);
		return lista;
	}
}
