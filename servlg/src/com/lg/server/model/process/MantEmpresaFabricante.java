package com.lg.server.model.process;

import java.util.Date;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.EmpresaFabricante;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicEmpresaFabricante;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantEmpresaFabricante {
	private static final Logger LOG = Logger.getLogger(MantEmpresaFabricante.class
			.getName());

	public static Boolean insertarEmpresaFabricante(EmpresaFabricante bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdEmpresaFabricante() != null) {					
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
				LogicEmpresaFabricante logic = new LogicEmpresaFabricante(pm);
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
			throw new UnknownException("Verifique Catalogo de Concesionario");
		}
	}

	public static Boolean actualizarEmpresaFabricante(EmpresaFabricante bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdEmpresaFabricante() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();				
				LogicEmpresaFabricante logic = new LogicEmpresaFabricante(pm);
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
			throw new UnknownException("Verifique Catalogo de Concesionario");
		}
	}

	public static Boolean eliminarEmpresaFabricante(EmpresaFabricante bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") 
				&& bean.getIdEmpresaFabricante() != null) {							
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(bean);
				parametro.setId(KeyFactory.stringToKey(bean.getIdEmpresaFabricante()));
				parametro.setTipoOperacion(bean.getOperacion());
				LogicEmpresaFabricante logic = new LogicEmpresaFabricante(pm);
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
			throw new UnknownException("Verifique Catalogo de Concesionario");
		}
	}

	public static List<EmpresaFabricante> listarEmpresaFabricante() throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicEmpresaFabricante logic = new LogicEmpresaFabricante(pm);
			List<EmpresaFabricante> resultado = (List<EmpresaFabricante>) logic.getListarBean();
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
