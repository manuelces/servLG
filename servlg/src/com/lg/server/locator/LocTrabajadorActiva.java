package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.TrabajadorActiva;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocTrabajadorActiva extends Locator<TrabajadorActiva, String>{

	@Override
	public TrabajadorActiva create(Class<? extends TrabajadorActiva> clazz) {
		// TODO Auto-generated method stub
		return new TrabajadorActiva();
	}

	@Override
	public TrabajadorActiva find(Class<? extends TrabajadorActiva> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			TrabajadorActiva bean = (TrabajadorActiva) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<TrabajadorActiva> getDomainType() {
		// TODO Auto-generated method stub
		return TrabajadorActiva.class;
	}

	@Override
	public String getId(TrabajadorActiva domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdTrabajadorActiva();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(TrabajadorActiva domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
