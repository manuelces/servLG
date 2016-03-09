package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.Electro;
import com.lg.server.model.dao.DaoElectro;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicElectro {
	
	private PersistenceManager pm;

	public LogicElectro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoElectro dao = new DaoElectro(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoElectro dao = new DaoElectro(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}	
	
	public Object getBean(Key id) throws UnknownException {
		DaoElectro dao = new DaoElectro(this.pm);
		return dao.getBean(id);
	}

	public Collection<Electro> getListarBean() throws UnknownException {
		DaoElectro dao = new DaoElectro(this.pm);
		Collection<Electro> lista = dao.getListarBean();
		return lista;
	}

}
