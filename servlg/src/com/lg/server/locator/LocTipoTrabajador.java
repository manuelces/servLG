package com.lg.server.locator;

import javax.jdo.PersistenceManager;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.TipoTrabajador;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocTipoTrabajador extends Locator<TipoTrabajador, String> {

	@Override
	public TipoTrabajador create(Class<? extends TipoTrabajador> clazz) {
		// TODO Auto-generated method stub
		return new TipoTrabajador();
	}

	@Override
	public TipoTrabajador find(Class<? extends TipoTrabajador> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {			
			TipoTrabajador bean = (TipoTrabajador) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<TipoTrabajador> getDomainType() {
		// TODO Auto-generated method stub
		return TipoTrabajador.class;
	}

	@Override
	public String getId(TipoTrabajador domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdTipoTrabajador();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(TipoTrabajador domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
