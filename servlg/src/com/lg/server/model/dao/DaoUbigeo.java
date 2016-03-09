package com.lg.server.model.dao;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.Ubigeo;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoUbigeo {

	private PersistenceManager pm;

	public DaoUbigeo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	@SuppressWarnings("unchecked")
	public Collection<Ubigeo> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Ubigeo> lista = (Collection<Ubigeo>) query
				.getListaBean(Ubigeo.class);
		return lista;
	}

}