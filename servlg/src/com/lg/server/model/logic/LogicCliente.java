package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Cliente;
import com.lg.server.model.dao.DaoCliente;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicCliente {
	private PersistenceManager pm;

	public LogicCliente(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		return dao.getBean(id);
	}		

	public Collection<Cliente> getListarBean() throws UnknownException {
		DaoCliente dao = new DaoCliente(this.pm);
		Collection<Cliente> lista = dao.getListarBean();
		return lista;
	}
}

