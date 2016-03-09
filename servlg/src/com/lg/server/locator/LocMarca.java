package com.lg.server.locator;

import javax.jdo.PersistenceManager;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Marca;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocMarca extends Locator<Marca, String> {

	@Override
	public Marca create(Class<? extends Marca> clazz) {
		// TODO Auto-generated method stub
		return new Marca();
	}

	@Override
	public Marca find(Class<? extends Marca> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {			
			Marca bean = (Marca) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Marca> getDomainType() {
		// TODO Auto-generated method stub
		return Marca.class;
	}

	@Override
	public String getId(Marca domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdMarca();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Marca domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
