package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.CorrelativoActiva;
import com.lg.server.model.dao.DaoCorrelativoActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicCorrelativoActiva {
	private PersistenceManager pm;

	public LogicCorrelativoActiva(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCorrelativoActiva dao = new DaoCorrelativoActiva(this.pm);
		return dao.mantenimiento(parametro);
	}

	public boolean deleteBulk(Collection<Object> list) throws UnknownException {
		DaoCorrelativoActiva dao = new DaoCorrelativoActiva(this.pm);
		return dao.deleteBulk(list);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCorrelativoActiva dao = new DaoCorrelativoActiva(this.pm);
		return dao.getBean(id);
	}

	public Collection<CorrelativoActiva> getListarBean()
			throws UnknownException {
		DaoCorrelativoActiva dao = new DaoCorrelativoActiva(this.pm);
		Collection<CorrelativoActiva> lista = dao.getListarBean();
		return lista;
	}

	public Collection<CorrelativoActiva> getListarBean(String codeCorrelativo)
			throws UnknownException {
		DaoCorrelativoActiva dao = new DaoCorrelativoActiva(this.pm);
		Collection<CorrelativoActiva> lista = dao
				.getListarBean(codeCorrelativo);
		return lista;
	}
}
