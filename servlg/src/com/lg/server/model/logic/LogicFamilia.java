package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.Familia;
import com.lg.server.model.dao.DaoFamilia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicFamilia {
	
	private PersistenceManager pm;

	public LogicFamilia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoFamilia dao = new DaoFamilia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoFamilia dao = new DaoFamilia(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}	
	
	public Object getBean(Key id) throws UnknownException {
		DaoFamilia dao = new DaoFamilia(this.pm);
		return dao.getBean(id);
	}

	public Collection<Familia> getListarBean() throws UnknownException {
		DaoFamilia dao = new DaoFamilia(this.pm);
		Collection<Familia> lista = dao.getListarBean();
		return lista;
	}

}
