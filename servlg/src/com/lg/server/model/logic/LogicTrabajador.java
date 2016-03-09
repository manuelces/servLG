package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.Trabajador;
import com.lg.server.model.dao.DaoTrabajador;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicTrabajador {

	private PersistenceManager pm;

	public LogicTrabajador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTrabajador dao = new DaoTrabajador(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTrabajador dao = new DaoTrabajador(this.pm);
		return dao.getBean(id);
	}

	public Collection<Trabajador> getListarBean() throws UnknownException {
		DaoTrabajador dao = new DaoTrabajador(this.pm);
		Collection<Trabajador> lista = dao.getListarBean();
		return lista;
	}

	public Collection<Trabajador> getListarBean(String codeTipoTrabajador)
			throws UnknownException {
		DaoTrabajador dao = new DaoTrabajador(this.pm);
		Collection<Trabajador> lista = dao.getListarBean(codeTipoTrabajador);
		return lista;
	}

}
