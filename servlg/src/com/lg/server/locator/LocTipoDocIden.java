package com.lg.server.locator;

import javax.jdo.PersistenceManager;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.TipoDocIden;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocTipoDocIden extends Locator<TipoDocIden, String> {

	@Override
	public TipoDocIden create(Class<? extends TipoDocIden> clazz) {
		// TODO Auto-generated method stub
		return new TipoDocIden();
	}

	@Override
	public TipoDocIden find(Class<? extends TipoDocIden> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			TipoDocIden bean = (TipoDocIden) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<TipoDocIden> getDomainType() {
		// TODO Auto-generated method stub
		return TipoDocIden.class;
	}

	@Override
	public String getId(TipoDocIden domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdTipoDoc();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(TipoDocIden domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
