package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.UsuarioCorrelativo;
import com.lg.server.model.dao.DaoUsuarioCorrelativo;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicUsuarioCorrelativo {
	private PersistenceManager pm;

	public LogicUsuarioCorrelativo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioCorrelativo dao = new DaoUsuarioCorrelativo(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuarioCorrelativo dao = new DaoUsuarioCorrelativo(this.pm);
		return dao.getBean(id);
	}

	public Collection<UsuarioCorrelativo> getListarBean()
			throws UnknownException {
		DaoUsuarioCorrelativo dao = new DaoUsuarioCorrelativo(this.pm);
		Collection<UsuarioCorrelativo> lista = dao.getListarBean();
		return lista;
	}
}
