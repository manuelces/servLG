package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.UsuarioCorrelativo;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoUsuarioCorrelativo {

	private PersistenceManager pm;

	public DaoUsuarioCorrelativo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(UsuarioCorrelativo.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<UsuarioCorrelativo> getListarBean()
			throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<UsuarioCorrelativo> lista = (Collection<UsuarioCorrelativo>) query
				.getListaBean(UsuarioCorrelativo.class);
		return lista;
	}

}
