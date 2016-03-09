package com.lg.server.model.process;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Cliente;
import com.lg.server.model.beans.Departamento;
import com.lg.server.model.beans.Distrito;
import com.lg.server.model.beans.Producto;
import com.lg.server.model.beans.Provincia;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicCliente;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantCliente {
	private static final Logger LOG = Logger.getLogger(MantCliente.class
			.getName());

	public static Boolean insertarCliente(Cliente bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdCliente() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				LogicCliente logic = new LogicCliente(pm);
				Boolean resultado = logic.mantenimiento(parametro);
				if (resultado) {
					tx.commit();
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}
			} catch (Exception ex) {
				LOG.warning(ex.getMessage());
				LOG.info(ex.getLocalizedMessage());
				throw new UnknownException(ex.getMessage());
			} finally {
				if (!pm.isClosed()) {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
			}
		} else {
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}

	public static Boolean actualizarCliente(Cliente bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdCliente() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();
				LogicCliente logic = new LogicCliente(pm);
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				Boolean resultado = logic.mantenimiento(parametro);
				if (resultado) {
					tx.commit();
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}
			} catch (Exception ex) {
				LOG.warning(ex.getMessage());
				LOG.info(ex.getLocalizedMessage());
				throw new UnknownException(ex.getMessage());
			} finally {
				if (!pm.isClosed()) {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
			}
		} else {
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}

	public static Boolean eliminarCliente(Cliente bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdCliente() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(bean);
				parametro.setId(bean.getIdCliente());
				parametro.setTipoOperacion(bean.getOperacion());
				LogicCliente logic = new LogicCliente(pm);
				Boolean resultado = logic.mantenimiento(parametro);
				if (resultado) {
					tx.commit();
					pm.close();
					return true;
				} else {
					tx.rollback();
					pm.close();
					return false;
				}
			} catch (Exception ex) {
				LOG.warning(ex.getMessage());
				LOG.info(ex.getLocalizedMessage());
				throw new UnknownException(ex.getMessage());
			} finally {
				if (!pm.isClosed()) {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
			}
		} else {
			throw new UnknownException("Verifique Catalogo de Servicio");
		}
	}

	public static List<Cliente> listarCliente() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Cliente.class, "ClienteGroup").addMembers("beanTipoDocIden","beanDistrito");
			PMF.getPMF().getFetchGroup(Distrito.class, "DistritoGroup").addMember("beanProvincia");
			PMF.getPMF().getFetchGroup(Provincia.class, "ProvinciaGroup").addMembers("beanDepartamento");
			PMF.getPMF().getFetchGroup(Departamento.class, "DepartamentoGroup").addMember("beanPais");
			pm.getFetchPlan().addGroup("ClienteGroup");
			pm.getFetchPlan().addGroup("DistritoGroup");
			pm.getFetchPlan().addGroup("ProvinciaGroup");
			pm.getFetchPlan().addGroup("DepartamentoGroup");
			pm.getFetchPlan().setMaxFetchDepth(-1);			
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicCliente logic = new LogicCliente(pm);
			List<Cliente> resultado = (List<Cliente>) pm.detachCopyAll(logic.getListarBean());
			Iterator<Cliente> iterador=resultado.iterator();
			while(iterador.hasNext()){
				Cliente bean=iterador.next();
			}
			tx.commit();
			pm.close();
			return resultado;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			if (!pm.isClosed()) {
				pm.close();
			}
		}

	}
}

