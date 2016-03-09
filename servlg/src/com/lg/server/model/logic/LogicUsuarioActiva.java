package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.UsuarioActiva;
import com.lg.server.model.dao.DaoUsuarioActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicUsuarioActiva {
	private PersistenceManager pm;

	public LogicUsuarioActiva(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioActiva dao = new DaoUsuarioActiva(this.pm);
		return dao.mantenimiento(parametro);
	}

	public boolean deleteBulk(Collection<Object> list) throws UnknownException {
		DaoUsuarioActiva dao = new DaoUsuarioActiva(this.pm);
		return dao.deleteBulk(list);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuarioActiva dao = new DaoUsuarioActiva(this.pm);
		return dao.getBean(id);
	}

	public Collection<UsuarioActiva> getListarBean() throws UnknownException {
		DaoUsuarioActiva dao = new DaoUsuarioActiva(this.pm);
		Collection<UsuarioActiva> lista = dao.getListarBean();
		return lista;
	}

	public Collection<UsuarioActiva> getListarBean(String codeTrabajador)
			throws UnknownException {
		DaoUsuarioActiva dao = new DaoUsuarioActiva(this.pm);
		Collection<UsuarioActiva> lista = dao.getListarBean(codeTrabajador);
		return lista;
	}
}
