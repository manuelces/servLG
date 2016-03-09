package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.lg.server.model.beans.SubFamilia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;
import javax.jdo.Query;

public class DaoSubFamilia {
	private PersistenceManager pm;

	public DaoSubFamilia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(Key id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SubFamilia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SubFamilia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SubFamilia> lista = (Collection<SubFamilia>) query
				.getListaBean(SubFamilia.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<SubFamilia> getListarBean(String codeFamilia) throws UnknownException {
		Query query = pm.newQuery(SubFamilia.class);	
		query.setFilter("codeFamilia == paramCodeFamilia && estadoActual == paramEstado");		
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeFamilia,Integer paramEstado");		
		try{
		List<SubFamilia> lista=new ArrayList<SubFamilia>();
		lista.addAll((List<SubFamilia>)query.execute(codeFamilia,1));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	
}
