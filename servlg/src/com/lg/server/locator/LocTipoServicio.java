package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.TipoServicio;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocTipoServicio extends Locator<TipoServicio, String> {

	@Override
	public TipoServicio create(Class<? extends TipoServicio> clazz) {
		// TODO Auto-generated method stub
		return new TipoServicio();
	}

	@Override
	public TipoServicio find(Class<? extends TipoServicio> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			TipoServicio bean = (TipoServicio) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<TipoServicio> getDomainType() {
		// TODO Auto-generated method stub
		return TipoServicio.class;
	}

	@Override
	public String getId(TipoServicio domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdTipoServicio();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(TipoServicio domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
