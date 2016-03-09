package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Producto;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocProducto extends Locator<Producto, String>{

	@Override
	public Producto create(Class<? extends Producto> clazz) {
		// TODO Auto-generated method stub
		return new Producto();
	}

	@Override
	public Producto find(Class<? extends Producto> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			System.out.println(id);
			Key key = KeyFactory.stringToKey(id);
			Producto bean = (Producto) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Producto> getDomainType() {
		// TODO Auto-generated method stub
		return Producto.class;
	}

	@Override
	public String getId(Producto domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdProducto();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Producto domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
