package com.lg.server.locator;

import javax.jdo.PersistenceManager;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.Trabajador;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocTrabajador extends Locator<Trabajador, String> {

	@Override
	public Trabajador create(Class<? extends Trabajador> clazz) {
		// TODO Auto-generated method stub
		return new Trabajador();
	}

	@Override
	public Trabajador find(Class<? extends Trabajador> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			Trabajador bean = (Trabajador) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<Trabajador> getDomainType() {
		// TODO Auto-generated method stub
		return Trabajador.class;
	}

	@Override
	public String getId(Trabajador domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdTrabajador();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(Trabajador domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
