package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.Electro;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicElectro;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantElectro {

	private static final Logger LOG = Logger.getLogger(MantElectro.class
			.getName());

	public static Boolean insertarElectro(Electro bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdElectro() != null) {					
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();	
				Date fechaServer=new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				LogicElectro logic = new LogicElectro(pm);
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

	public static Boolean actualizarElectro(Electro bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdElectro() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();				
				LogicElectro logic = new LogicElectro(pm);				
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

	public static Boolean eliminarElectro(Electro bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") 
				&& bean.getIdElectro() != null) {							
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(bean);
				parametro.setId(KeyFactory.stringToKey(bean.getIdElectro()));
				parametro.setTipoOperacion(bean.getOperacion());
				LogicElectro logic = new LogicElectro(pm);
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

	public static List<Electro> listarElectro() throws UnknownException {

		PersistenceManager pm = null;
		//Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			//PMF.getPMF().getFetchGroup(Electro.class, "ElectroGroup").addMember("listSubElectro");
			//pm.getFetchPlan().addGroup("ElectroGroup");
			//pm.getFetchPlan().setMaxFetchDepth(1);
			//tx = pm.currentTransaction();
			//tx.begin();
			//pm.setDetachAllOnCommit(true);
			LogicElectro logic = new LogicElectro(pm);
			//List<Electro> resultado = (List<Electro>) pm.detachCopyAll( logic.getListarBean());
			List<Electro> resultado =(List<Electro>) logic.getListarBean();
			//tx.commit();
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
