package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Correlativo;
import com.lg.server.model.dao.DaoCorrelativo;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicCorrelativo {
	private PersistenceManager pm;

	public LogicCorrelativo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoCorrelativo dao = new DaoCorrelativo(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoCorrelativo dao = new DaoCorrelativo(this.pm);
		return dao.getBean(id);
	}

	public Collection<Correlativo> getListarBean() throws UnknownException {
		DaoCorrelativo dao = new DaoCorrelativo(this.pm);
		Collection<Correlativo> lista = dao.getListarBean();
		return lista;
	}

	public Collection<Correlativo> getListarBean(String codeTipoDoc)
			throws UnknownException {
		DaoCorrelativo dao = new DaoCorrelativo(this.pm);
		Collection<Correlativo> lista = dao.getListarBean(codeTipoDoc);
		return lista;
	}
}
