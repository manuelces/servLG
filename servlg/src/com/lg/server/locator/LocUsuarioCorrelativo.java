package com.lg.server.locator;

import javax.jdo.PersistenceManager;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.UsuarioCorrelativo;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocUsuarioCorrelativo extends Locator<UsuarioCorrelativo, String> {

	@Override
	public UsuarioCorrelativo create(Class<? extends UsuarioCorrelativo> clazz) {
		// TODO Auto-generated method stub
		return new UsuarioCorrelativo();
	}

	@Override
	public UsuarioCorrelativo find(Class<? extends UsuarioCorrelativo> clazz,
			String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			UsuarioCorrelativo bean = (UsuarioCorrelativo) query.getBean(clazz,
					id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<UsuarioCorrelativo> getDomainType() {
		// TODO Auto-generated method stub
		return UsuarioCorrelativo.class;
	}

	@Override
	public String getId(UsuarioCorrelativo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdUsuarioCorrelativo();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(UsuarioCorrelativo domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion() == null ? 0 : domainObject
				.getVersion();
	}

}
