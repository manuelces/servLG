package com.lg.server.model.dao;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.TipoDocumento;
import com.lg.server.model.dao.Querys;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoTipoDocumento {

	private PersistenceManager pm;

	public DaoTipoDocumento(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(TipoDocumento.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TipoDocumento> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<TipoDocumento> lista = (Collection<TipoDocumento>) query
				.getListaBean(TipoDocumento.class);
		return lista;
	}

}
