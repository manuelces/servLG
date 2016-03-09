package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.lg.server.model.beans.Provincia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoProvincia {
	private PersistenceManager pm;

	public DaoProvincia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Provincia.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Provincia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Provincia> lista = (Collection<Provincia>) query
				.getListaBean(Provincia.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Provincia> getListarBean(String codeDepartamento) throws UnknownException {
		Query query = pm.newQuery(Provincia.class);	
		query.setFilter("codeDepartamento == paramCodeDepartamento");		
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodeDepartamento");		
		try{
		List<Provincia> lista=new ArrayList<Provincia>();
		lista.addAll((List<Provincia>)query.execute(codeDepartamento));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
}
