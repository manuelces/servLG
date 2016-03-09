package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.lg.server.model.beans.EmpresaFabricante;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.web.bindery.requestfactory.shared.Locator;

public class LocEmpresaFabricante extends Locator<EmpresaFabricante, String> {
	@Override
	public EmpresaFabricante create(Class<? extends EmpresaFabricante> clazz) {
		// TODO Auto-generated method stub
		return new EmpresaFabricante();
	}

	@Override
	public EmpresaFabricante find(Class<? extends EmpresaFabricante> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Key key = KeyFactory.stringToKey(id);
			EmpresaFabricante bean = (EmpresaFabricante) query.getBean(clazz, key);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<EmpresaFabricante> getDomainType() {
		// TODO Auto-generated method stub
		return EmpresaFabricante.class;
	}

	@Override
	public String getId(EmpresaFabricante domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdEmpresaFabricante();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(EmpresaFabricante domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}
}
