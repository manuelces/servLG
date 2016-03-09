package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Departamento;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocDepartamento extends Locator<Departamento, String>{

	@Override
	public Departamento create(Class<? extends Departamento> clazz) {
		// TODO Auto-generated method stub
		return new Departamento();
	}

	@Override
	public Departamento find(Class<? extends Departamento> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {			
			Departamento bean = (Departamento) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Departamento> getDomainType() {
		// TODO Auto-generated method stub
		return Departamento.class;
	}

	@Override
	public String getId(Departamento domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdDepartamento();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Departamento domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion()==null?0:domainObject.getVersion();
	}

}
