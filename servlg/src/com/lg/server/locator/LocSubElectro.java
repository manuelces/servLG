package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.SubElectro;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocSubElectro extends Locator<SubElectro, String> {

	@Override
	public SubElectro create(Class<? extends SubElectro> clazz) {
		// TODO Auto-generated method stub
		return new SubElectro();
	}

	@Override
	public SubElectro find(Class<? extends SubElectro> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			SubElectro bean = (SubElectro) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<SubElectro> getDomainType() {
		// TODO Auto-generated method stub
		return SubElectro.class;
	}

	@Override
	public String getId(SubElectro domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdSubElectro();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(SubElectro domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
