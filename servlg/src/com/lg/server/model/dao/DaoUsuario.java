package com.lg.server.model.dao;

//import java.util.ArrayList;
import java.util.Collection;
//import java.util.List;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.Usuario;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

//import javax.jdo.Query;

public class DaoUsuario {
	private PersistenceManager pm;

	public DaoUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Usuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Usuario> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Usuario> lista = (Collection<Usuario>) query
				.getListaBean(Usuario.class);
		return lista;
	}

	/*
	 * @SuppressWarnings("unchecked") public Collection<Usuario>
	 * getListarBean(String codeTipoTrabajador) throws UnknownException { Query
	 * query = pm.newQuery(Trabajador.class); query.setFilter(
	 * "codeTipoTrabajador == paramCodeTipoTrabajador && estadoActual == paramEstado"
	 * ); query.setOrdering("paterno asc");
	 * query.declareParameters("String paramCodeTipoTrabajador,Integer paramEstado"
	 * ); try { List<Trabajador> lista = new ArrayList<Trabajador>();
	 * lista.addAll((List<Trabajador>) query .execute(codeTipoTrabajador, 1));
	 * return lista; } catch (Exception ex) { throw new
	 * UnknownException(ex.getMessage()); } finally { query.closeAll(); } }
	 */
}
