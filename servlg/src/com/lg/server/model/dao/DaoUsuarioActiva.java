package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.lg.server.model.beans.UsuarioActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoUsuarioActiva {
	private PersistenceManager pm;

	public DaoUsuarioActiva(PersistenceManager pm) {
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
		return query.getBean(UsuarioActiva.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<UsuarioActiva> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<UsuarioActiva> lista = (Collection<UsuarioActiva>) query
				.getListaBean(UsuarioActiva.class);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Collection<UsuarioActiva> getListarBean(String codeUsuario)
			throws UnknownException {
		Query query = pm.newQuery(UsuarioActiva.class);
		query.setFilter("codeUsuario == paramCodeUsuario");
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeUsuario");
		try {
			List<UsuarioActiva> lista = new ArrayList<UsuarioActiva>();
			lista.addAll((List<UsuarioActiva>) query.execute(codeUsuario));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}
}
