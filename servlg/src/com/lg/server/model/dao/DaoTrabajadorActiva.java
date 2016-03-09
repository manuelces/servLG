package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.lg.server.model.beans.TrabajadorActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoTrabajadorActiva {
	private PersistenceManager pm;

	public DaoTrabajadorActiva(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public boolean deleteBulk(Collection<Object> list) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.deleteBulk(list);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TrabajadorActiva.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TrabajadorActiva> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TrabajadorActiva> lista = (Collection<TrabajadorActiva>) query
				.getListaBean(TrabajadorActiva.class);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Collection<TrabajadorActiva> getListarBean(String codeTrabajador)
			throws UnknownException {
		Query query = pm.newQuery(TrabajadorActiva.class);
		query.setFilter("codeTrabajador == paramCodeTrabajador");
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeTrabajador");
		try {
			List<TrabajadorActiva> lista = new ArrayList<TrabajadorActiva>();
			lista.addAll((List<TrabajadorActiva>) query.execute(codeTrabajador));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
