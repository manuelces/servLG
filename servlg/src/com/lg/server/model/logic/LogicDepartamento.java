package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Departamento;
import com.lg.server.model.dao.DaoDepartamento;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicDepartamento {
	private PersistenceManager pm;

	public LogicDepartamento(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoDepartamento dao = new DaoDepartamento(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoDepartamento dao = new DaoDepartamento(this.pm);
		return dao.getBean(id);
	}
	
	public Collection<Departamento> getListarBean() throws UnknownException {
		DaoDepartamento dao = new DaoDepartamento(this.pm);
		Collection<Departamento> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Departamento> getListarBean(String codePais) throws UnknownException {
		DaoDepartamento dao = new DaoDepartamento(this.pm);
		Collection<Departamento> lista = dao.getListarBean(codePais);
		return lista;
	}
}
