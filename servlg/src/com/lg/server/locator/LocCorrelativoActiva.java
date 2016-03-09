package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.CorrelativoActiva;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocCorrelativoActiva extends Locator<CorrelativoActiva, String>{

	@Override
	public CorrelativoActiva create(Class<? extends CorrelativoActiva> clazz) {
		// TODO Auto-generated method stub
		return new CorrelativoActiva();
	}

	@Override
	public CorrelativoActiva find(Class<? extends CorrelativoActiva> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			CorrelativoActiva bean = (CorrelativoActiva) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<CorrelativoActiva> getDomainType() {
		// TODO Auto-generated method stub
		return CorrelativoActiva.class;
	}

	@Override
	public String getId(CorrelativoActiva domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdCorrelativoActiva();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(CorrelativoActiva domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
