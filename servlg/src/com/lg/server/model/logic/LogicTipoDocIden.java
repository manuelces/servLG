package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.TipoDocIden;
import com.lg.server.model.dao.DaoTipoDocIden;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicTipoDocIden {

	private PersistenceManager pm;

	public LogicTipoDocIden(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoDocIden dao = new DaoTipoDocIden(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTipoDocIden dao = new DaoTipoDocIden(this.pm);
		return dao.getBean(id);
	}

	public Collection<TipoDocIden> getListarBean() throws UnknownException {
		DaoTipoDocIden dao = new DaoTipoDocIden(this.pm);
		Collection<TipoDocIden> lista = dao.getListarBean();
		return lista;
	}
	
}
