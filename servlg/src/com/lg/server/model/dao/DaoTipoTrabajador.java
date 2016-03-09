package com.lg.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.TipoTrabajador;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoTipoTrabajador {

	private PersistenceManager pm;

	public DaoTipoTrabajador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoTrabajador.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoTrabajador> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoTrabajador> lista = (Collection<TipoTrabajador>) query
				.getListaBean(TipoTrabajador.class);
		return lista;
	}

}
