package com.lg.server.model.logic;

import java.util.Collection;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.EmpresaFabricante;
import com.lg.server.model.dao.DaoEmpresaFabricante;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicEmpresaFabricante {

	private PersistenceManager pm;

	public LogicEmpresaFabricante(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEmpresaFabricante dao = new DaoEmpresaFabricante(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		DaoEmpresaFabricante dao = new DaoEmpresaFabricante(this.pm);
		return dao.getBean(id);
	}
	public Object getBean(String id) throws UnknownException {
		DaoEmpresaFabricante dao = new DaoEmpresaFabricante(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}

	public Collection<EmpresaFabricante> getListarBean()
			throws UnknownException {
		DaoEmpresaFabricante dao = new DaoEmpresaFabricante(this.pm);
		Collection<EmpresaFabricante> lista = dao.getListarBean();
		return lista;
	}

}
