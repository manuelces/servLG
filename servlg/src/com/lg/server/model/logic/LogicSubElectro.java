package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.SubElectro;
import com.lg.server.model.dao.DaoSubElectro;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicSubElectro {
	private PersistenceManager pm;

	public LogicSubElectro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSubElectro dao = new DaoSubElectro(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSubElectro dao = new DaoSubElectro(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}
	
	public Object getBean(Key id) throws UnknownException {
		DaoSubElectro dao = new DaoSubElectro(this.pm);
		return dao.getBean(id);
	}

	public Collection<SubElectro> getListarBean() throws UnknownException {
		DaoSubElectro dao = new DaoSubElectro(this.pm);
		Collection<SubElectro> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<SubElectro> getListarBean(String codeElectro) throws UnknownException {
		DaoSubElectro dao = new DaoSubElectro(this.pm);
		Collection<SubElectro> lista = dao.getListarBean(codeElectro);
		return lista;
	}
}
