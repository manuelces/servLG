package com.lg.server.model.logic;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.TipoDocumento;
import com.lg.server.model.dao.DaoTipoDocumento;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicTipoDocumento {

	private PersistenceManager pm;

	public LogicTipoDocumento(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoDocumento dao = new DaoTipoDocumento(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTipoDocumento dao = new DaoTipoDocumento(this.pm);
		return dao.getBean(id);
	}

	public Collection<TipoDocumento> getListarBean() throws UnknownException {
		DaoTipoDocumento dao = new DaoTipoDocumento(this.pm);
		Collection<TipoDocumento> lista = dao.getListarBean();
		return lista;
	}

}
