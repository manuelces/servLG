package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Distrito;
import com.lg.server.model.dao.DaoDistrito;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicDistrito {
	private PersistenceManager pm;

	public LogicDistrito(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoDistrito dao = new DaoDistrito(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoDistrito dao = new DaoDistrito(this.pm);
		return dao.getBean(id);
	}
	
	public Collection<Distrito> getListarBean() throws UnknownException {
		DaoDistrito dao = new DaoDistrito(this.pm);
		Collection<Distrito> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Distrito> getListarBean(String codePais) throws UnknownException {
		DaoDistrito dao = new DaoDistrito(this.pm);
		Collection<Distrito> lista = dao.getListarBean(codePais);
		return lista;
	}
}
