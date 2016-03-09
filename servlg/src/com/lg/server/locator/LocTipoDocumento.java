package com.lg.server.locator;

import javax.jdo.PersistenceManager;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.lg.server.model.beans.TipoDocumento;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.dao.Querys;

public class LocTipoDocumento extends Locator<TipoDocumento, String>{
	
	@Override
	public TipoDocumento create(Class<? extends TipoDocumento> clazz) {
		// TODO Auto-generated method stub
		return new TipoDocumento();
	}

	@Override
	public TipoDocumento find(Class<? extends TipoDocumento> clazz, String id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		Querys query = new Querys(pm);
		try {
			TipoDocumento bean = (TipoDocumento) query.getBean(clazz, id);
			pm.close();
			return bean;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Class<TipoDocumento> getDomainType() {
		// TODO Auto-generated method stub
		return TipoDocumento.class;
	}

	@Override
	public String getId(TipoDocumento domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getIdTipoDoc();
	}

	@Override
	public Class<String> getIdType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public Object getVersion(TipoDocumento domainObject) {
		// TODO Auto-generated method stub
		return domainObject.getVersion();
	}

}
