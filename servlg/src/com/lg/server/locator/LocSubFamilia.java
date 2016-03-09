package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.SubFamilia;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocSubFamilia extends Locator<SubFamilia, String>{

	@Override
	public SubFamilia create(Class<? extends SubFamilia> clazz) {
		// TODO Auto-generated method stub
		return new SubFamilia();
	}

	@Override
	public SubFamilia find(Class<? extends SubFamilia> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			SubFamilia bean = (SubFamilia) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<SubFamilia> getDomainType() {
		// TODO Auto-generated method stub
		return SubFamilia.class;
	}

	@Override
	public String getId(SubFamilia domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdSubFamilia();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(SubFamilia domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
