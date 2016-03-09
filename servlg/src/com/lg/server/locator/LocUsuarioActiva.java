package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.UsuarioActiva;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocUsuarioActiva extends Locator<UsuarioActiva, String> {

	@Override
	public UsuarioActiva create(Class<? extends UsuarioActiva> clazz) {
		// TODO Auto-generated method stub
		return new UsuarioActiva();
	}

	@Override
	public UsuarioActiva find(Class<? extends UsuarioActiva> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			UsuarioActiva bean = (UsuarioActiva) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<UsuarioActiva> getDomainType() {
		// TODO Auto-generated method stub
		return UsuarioActiva.class;
	}

	@Override
	public String getId(UsuarioActiva domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdUsuarioActiva();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(UsuarioActiva domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
