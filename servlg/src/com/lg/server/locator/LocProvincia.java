package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Provincia;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocProvincia extends Locator<Provincia, String>{

	@Override
	public Provincia create(Class<? extends Provincia> clazz) {
		// TODO Auto-generated method stub
		return new Provincia();
	}

	@Override
	public Provincia find(Class<? extends Provincia> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Provincia bean = (Provincia) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Provincia> getDomainType() {
		// TODO Auto-generated method stub
		return Provincia.class;
	}

	@Override
	public String getId(Provincia domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdProvincia();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Provincia domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
