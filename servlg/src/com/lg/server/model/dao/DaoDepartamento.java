package com.lg.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.lg.server.model.beans.Departamento;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class DaoDepartamento {
	private PersistenceManager pm;

	public DaoDepartamento(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Departamento.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Departamento> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Departamento> lista = (Collection<Departamento>) query
				.getListaBean(Departamento.class);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Departamento> getListarBean(String codePais) throws UnknownException {
		Query query = pm.newQuery(Departamento.class);	
		query.setFilter("codePais == paramCodePais");		
		query.setOrdering("descripcion asc");
		query.declareParameters("String paramCodePais");		
		try{
		List<Departamento> lista=new ArrayList<Departamento>();
		lista.addAll((List<Departamento>)query.execute(codePais));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	
}
