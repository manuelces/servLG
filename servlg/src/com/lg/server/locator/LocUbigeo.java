package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Ubigeo;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocUbigeo extends Locator<Ubigeo, String> {

	@Override
	public Ubigeo create(Class<? extends Ubigeo> clazz) {
		// TODO Auto-generated method stub
		return new Ubigeo();
	}

	@Override
	public Ubigeo find(Class<? extends Ubigeo> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Ubigeo bean = (Ubigeo) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Ubigeo> getDomainType() {
		// TODO Auto-generated method stub
		return Ubigeo.class;
	}

	@Override
	public String getId(Ubigeo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdUbigeo();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Ubigeo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
