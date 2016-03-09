package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.lg.server.model.beans.CorrelativoActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoCorrelativoActiva {
	private PersistenceManager pm;

	public DaoCorrelativoActiva(PersistenceManager pm) {
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
		return query.getBean(CorrelativoActiva.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<CorrelativoActiva> getListarBean()
			throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<CorrelativoActiva> lista = (Collection<CorrelativoActiva>) query
				.getListaBean(CorrelativoActiva.class);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Collection<CorrelativoActiva> getListarBean(String codeCorrelativo)
			throws UnknownException {
		Query query = pm.newQuery(CorrelativoActiva.class);
		query.setFilter("codeCorrelativo == paramCodeCorrelativo");
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeCorrelativo");
		try {
			List<CorrelativoActiva> lista = new ArrayList<CorrelativoActiva>();
			lista.addAll((List<CorrelativoActiva>) query
					.execute(codeCorrelativo));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
