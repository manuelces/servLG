package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.TipoDocIden;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoTipoDocIden {

	private PersistenceManager pm;

	public DaoTipoDocIden(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoDocIden.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoDocIden> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoDocIden> lista = (Collection<TipoDocIden>) query
				.getListaBean(TipoDocIden.class);
		return lista;
	}

}
