package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Pais;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocPais extends Locator<Pais, String>{

	@Override
	public Pais create(Class<? extends Pais> clazz) {
		// TODO Auto-generated method stub
		return new Pais();
	}

	@Override
	public Pais find(Class<? extends Pais> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Pais bean = (Pais) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Pais> getDomainType() {
		// TODO Auto-generated method stub
		return Pais.class;
	}

	@Override
	public String getId(Pais domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdPais();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Pais domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
