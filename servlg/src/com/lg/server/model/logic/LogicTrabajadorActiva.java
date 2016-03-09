package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.TrabajadorActiva;
import com.lg.server.model.dao.DaoTrabajadorActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicTrabajadorActiva {
	private PersistenceManager pm;

	public LogicTrabajadorActiva(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTrabajadorActiva dao = new DaoTrabajadorActiva(this.pm);
		return dao.mantenimiento(parametro);
	}

	public boolean deleteBulk(Collection<Object> list) throws UnknownException {
		DaoTrabajadorActiva dao = new DaoTrabajadorActiva(this.pm);
		return dao.deleteBulk(list);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTrabajadorActiva dao = new DaoTrabajadorActiva(this.pm);
		return dao.getBean(id);
	}

	public Collection<TrabajadorActiva> getListarBean() throws UnknownException {
		DaoTrabajadorActiva dao = new DaoTrabajadorActiva(this.pm);
		Collection<TrabajadorActiva> lista = dao.getListarBean();
		return lista;
	}

	public Collection<TrabajadorActiva> getListarBean(String codeTrabajador)
			throws UnknownException {
		DaoTrabajadorActiva dao = new DaoTrabajadorActiva(this.pm);
		Collection<TrabajadorActiva> lista = dao.getListarBean(codeTrabajador);
		return lista;
	}
}
