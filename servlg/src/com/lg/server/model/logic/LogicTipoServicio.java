package com.lg.server.model.logic;

import java.util.Collection;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.TipoServicio;
import com.lg.server.model.dao.DaoTipoServicio;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

//import com.google.appengine.api.datastore.KeyFactory;

public class LogicTipoServicio {
	private PersistenceManager pm;

	public LogicTipoServicio(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoServicio dao = new DaoTipoServicio(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		DaoTipoServicio dao = new DaoTipoServicio(this.pm);
		return dao.getBean(id);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTipoServicio dao = new DaoTipoServicio(this.pm);
		return dao.getBean(KeyFactory.stringToKey(id));
	}

	public Collection<TipoServicio> getListarBean() throws UnknownException {
		DaoTipoServicio dao = new DaoTipoServicio(this.pm);
		Collection<TipoServicio> lista = dao.getListarBean();
		return lista;
	}
	public Collection<TipoServicio> getListarBean(String codeEmpresaFabricante)
			throws UnknownException {
		DaoTipoServicio dao = new DaoTipoServicio(this.pm);
		Collection<TipoServicio> lista = dao.getListarBean(codeEmpresaFabricante);
		return lista;
	}
}
