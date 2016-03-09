package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.TipoTrabajador;
import com.lg.server.model.dao.DaoTipoTrabajador;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicTipoTrabajador {

	private PersistenceManager pm;

	public LogicTipoTrabajador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoTrabajador dao = new DaoTipoTrabajador(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTipoTrabajador dao = new DaoTipoTrabajador(this.pm);
		return dao.getBean(id);
	}

	public Collection<TipoTrabajador> getListarBean() throws UnknownException {
		DaoTipoTrabajador dao = new DaoTipoTrabajador(this.pm);
		Collection<TipoTrabajador> lista = dao.getListarBean();
		return lista;
	}

}
