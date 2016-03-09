package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Familia;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocFamilia extends Locator<Familia, String>{

	@Override
	public Familia create(Class<? extends Familia> clazz) {
		// TODO Auto-generated method stub
		return new Familia();
	}

	@Override
	public Familia find(Class<? extends Familia> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			Familia bean = (Familia) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Familia> getDomainType() {
		// TODO Auto-generated method stub
		return Familia.class;
	}

	@Override
	public String getId(Familia domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdFamilia();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Familia domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
