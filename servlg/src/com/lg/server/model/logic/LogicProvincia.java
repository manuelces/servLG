package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.Provincia;
import com.lg.server.model.dao.DaoProvincia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicProvincia {
	private PersistenceManager pm;

	public LogicProvincia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoProvincia dao = new DaoProvincia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoProvincia dao = new DaoProvincia(this.pm);
		return dao.getBean(id);
	}
	
	public Collection<Provincia> getListarBean() throws UnknownException {
		DaoProvincia dao = new DaoProvincia(this.pm);
		Collection<Provincia> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<Provincia> getListarBean(String codePais) throws UnknownException {
		DaoProvincia dao = new DaoProvincia(this.pm);
		Collection<Provincia> lista = dao.getListarBean(codePais);
		return lista;
	}
}

