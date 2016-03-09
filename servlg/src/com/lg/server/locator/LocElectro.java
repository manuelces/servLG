package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Electro;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocElectro extends Locator<Electro, String>{

	@Override
	public Electro create(Class<? extends Electro> clazz) {
		// TODO Auto-generated method stub
		return new Electro();
	}

	@Override
	public Electro find(Class<? extends Electro> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			Electro bean = (Electro) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Electro> getDomainType() {
		// TODO Auto-generated method stub
		return Electro.class;
	}

	@Override
	public String getId(Electro domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdElectro();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Electro domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
