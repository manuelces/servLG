package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Distrito;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocDistrito extends Locator<Distrito, String>{

	@Override
	public Distrito create(Class<? extends Distrito> clazz) {
		// TODO Auto-generated method stub
		return new Distrito();
	}

	@Override
	public Distrito find(Class<? extends Distrito> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Distrito bean = (Distrito) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Distrito> getDomainType() {
		// TODO Auto-generated method stub
		return Distrito.class;
	}

	@Override
	public String getId(Distrito domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdDistrito();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Distrito domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
