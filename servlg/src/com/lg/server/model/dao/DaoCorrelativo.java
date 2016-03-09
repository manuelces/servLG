package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.lg.server.model.beans.Correlativo;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoCorrelativo {
	private PersistenceManager pm;

	public DaoCorrelativo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Correlativo.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Correlativo> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Correlativo> lista = (Collection<Correlativo>) query
				.getListaBean(Correlativo.class);
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Collection<Correlativo> getListarBean(String codeTipoDoc)
			throws UnknownException {
		Query query = pm.newQuery(Correlativo.class);
		query.setFilter("codeTipoDoc == paramCodeTipoDoc && estadoActual == paramEstado");
		query.setOrdering("serie asc");
		query.declareParameters("String paramCodeTipoDoc,Integer paramEstado");
		try {
			List<Correlativo> lista = new ArrayList<Correlativo>();
			lista.addAll((List<Correlativo>) query.execute(codeTipoDoc, 1));
			return lista;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	/*
	 * @SuppressWarnings("unchecked") public Collection<Trabajador>
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
