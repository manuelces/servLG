package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Cliente;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocCliente extends Locator<Cliente, String> {

	@Override
	public Cliente create(Class<? extends Cliente> clazz) {
		// TODO Auto-generated method stub
		return new Cliente();
	}

	@Override
	public Cliente find(Class<? extends Cliente> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Cliente bean = (Cliente) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Cliente> getDomainType() {
		// TODO Auto-generated method stub
		return Cliente.class;
	}

	@Override
	public String getId(Cliente domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdCliente();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Cliente domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
