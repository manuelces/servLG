package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.EmpresaFabricante;
import com.lg.server.model.dao.Querys;
import com.google.appengine.api.datastore.Key;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoEmpresaFabricante {
	private PersistenceManager pm;

	public DaoEmpresaFabricante(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(EmpresaFabricante.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<EmpresaFabricante> getListarBean()
			throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<EmpresaFabricante> lista = (Collection<EmpresaFabricante>) query
				.getListaBean(EmpresaFabricante.class);
		return lista;
	}
}
