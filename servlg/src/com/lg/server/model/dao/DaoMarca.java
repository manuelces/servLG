package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Marca;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoMarca {
	private PersistenceManager pm;

	public DaoMarca(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Marca.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Marca> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Marca> lista = (Collection<Marca>) query
				.getListaBean(Marca.class);
		return lista;
	}
}
