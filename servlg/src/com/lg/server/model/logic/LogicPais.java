package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Pais;
import com.lg.server.model.dao.DaoPais;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicPais {
	private PersistenceManager pm;

	public LogicPais(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		return dao.getBean(id);
	}		

	public Collection<Pais> getListarBean() throws UnknownException {
		DaoPais dao = new DaoPais(this.pm);
		Collection<Pais> lista = dao.getListarBean();
		return lista;
	}
}
