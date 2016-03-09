package com.lg.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.SubFamilia;
import com.lg.server.model.dao.DaoSubFamilia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicSubFamilia {
	private PersistenceManager pm;

	public LogicSubFamilia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSubFamilia dao = new DaoSubFamilia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSubFamilia dao = new DaoSubFamilia(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}
	
	public Object getBean(Key id) throws UnknownException {
		DaoSubFamilia dao = new DaoSubFamilia(this.pm);
		return dao.getBean(id);
	}

	public Collection<SubFamilia> getListarBean() throws UnknownException {
		DaoSubFamilia dao = new DaoSubFamilia(this.pm);
		Collection<SubFamilia> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<SubFamilia> getListarBean(String codeFamilia) throws UnknownException {
		DaoSubFamilia dao = new DaoSubFamilia(this.pm);
		Collection<SubFamilia> lista = dao.getListarBean(codeFamilia);
		return lista;
	}
}
