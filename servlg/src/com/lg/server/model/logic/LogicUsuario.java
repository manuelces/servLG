package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.Usuario;
import com.lg.server.model.dao.DaoUsuario;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicUsuario {

	private PersistenceManager pm;

	public LogicUsuario(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		return dao.getBean(id);
	}

	public Collection<Usuario> getListarBean() throws UnknownException {
		DaoUsuario dao = new DaoUsuario(this.pm);
		Collection<Usuario> lista = dao.getListarBean();
		return lista;
	}

	/*
	 * public Collection<Trabajador> getListarBean(String codeTipoTrabajador)
	 * throws UnknownException { DaoTrabajador dao = new DaoTrabajador(this.pm);
	 * Collection<Trabajador> lista = dao.getListarBean(codeTipoTrabajador);
	 * return lista; }
	 */

}
