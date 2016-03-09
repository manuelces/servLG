package com.lg.server.locator;

import javax.jdo.PersistenceManager;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Correlativo;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocCorrelativo extends Locator<Correlativo, String> {

	@Override
	public Correlativo create(Class<? extends Correlativo> clazz) {
		// TODO Auto-generated method stub
		return new Correlativo();
	}

	@Override
	public Correlativo find(Class<? extends Correlativo> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Correlativo bean = (Correlativo) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Correlativo> getDomainType() {
		// TODO Auto-generated method stub
		return Correlativo.class;
	}

	@Override
	public String getId(Correlativo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdCorrelativo();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Correlativo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
