package com.lg.server.model.logic;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.Ubigeo;
import com.lg.server.model.dao.DaoUbigeo;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicUbigeo {
	
	private PersistenceManager pm;

	public LogicUbigeo(PersistenceManager pm) {
		this.pm = pm;
	}
	
	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUbigeo dao = new DaoUbigeo(this.pm);
		return dao.mantenimiento(parametro);
	}
	
	public Collection<Ubigeo> getListarBean() throws UnknownException {
		DaoUbigeo dao = new DaoUbigeo(this.pm);
		Collection<Ubigeo> lista = dao.getListarBean();
		return lista;
	}
	
}