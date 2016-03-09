package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.lg.server.model.beans.SubElectro;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;
import javax.jdo.Query;

public class DaoSubElectro {
	private PersistenceManager pm;

	public DaoSubElectro(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SubElectro.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SubElectro> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SubElectro> lista = (Collection<SubElectro>) query
				.getListaBean(SubElectro.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<SubElectro> getListarBean(String codeElectro) throws UnknownException {
		Query query = pm.newQuery(SubElectro.class);	
		query.setFilter("codeElectro == paramCodeElectro && estadoActual == paramEstado");		
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeElectro,Integer paramEstado");		
		try{
		List<SubElectro> lista=new ArrayList<SubElectro>();
		lista.addAll((List<SubElectro>)query.execute(codeElectro,1));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	
}
