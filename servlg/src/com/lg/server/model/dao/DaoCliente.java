package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Cliente;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoCliente {
	private PersistenceManager pm;

	public DaoCliente(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Cliente> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Cliente> lista = (Collection<Cliente>) query
				.getListaBean(Cliente.class);
		return lista;
	}
}

