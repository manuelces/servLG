package com.lg.server.model.logic;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.lg.server.model.beans.Marca;
import com.lg.server.model.dao.DaoMarca;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class LogicMarca {

	private PersistenceManager pm;

	public LogicMarca(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoMarca dao = new DaoMarca(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoMarca dao = new DaoMarca(this.pm);
		return dao.getBean(id);
	}

	public Collection<Marca> getListarBean() throws UnknownException {
		DaoMarca dao = new DaoMarca(this.pm);
		Collection<Marca> lista = dao.getListarBean();
		return lista;
	}

}
