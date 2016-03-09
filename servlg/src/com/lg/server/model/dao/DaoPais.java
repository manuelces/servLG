package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Pais;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoPais {
	private PersistenceManager pm;

	public DaoPais(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Pais.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Pais> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Pais> lista = (Collection<Pais>) query
				.getListaBean(Pais.class);
		return lista;
	}
}
