package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.lg.server.model.beans.TipoServicio;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoTipoServicio {
	private PersistenceManager pm;

	public DaoTipoServicio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoServicio.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoServicio> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoServicio> lista = (Collection<TipoServicio>) query
				.getListaBean(TipoServicio.class);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoServicio> getListarBean(String codeEmpresaFabricante)
			throws UnknownException {
		Query query = pm.newQuery(TipoServicio.class);
		query.setFilter("codeEmpresaFabricante == paramCodeEmpresaFabricante && estadoActual == paramEstado");
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeEmpresaFabricante,Integer paramEstado");
		try {
			List<TipoServicio> lista = new ArrayList<TipoServicio>();
			lista.addAll((List<TipoServicio>) query.execute(
					codeEmpresaFabricante, 1));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
