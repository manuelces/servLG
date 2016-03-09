package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.lg.server.model.beans.Electro;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoElectro {
	private PersistenceManager pm;

	public DaoElectro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Electro.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Electro> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Electro> lista = (Collection<Electro>) query
				.getListaBean(Electro.class);
		return lista;
	}
}
