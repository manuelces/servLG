package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Correlativo;
import com.lg.server.model.beans.CorrelativoActiva;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicCorrelativo;
import com.lg.server.model.logic.LogicCorrelativoActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantCorrelativo {

	private static final Logger LOG = Logger.getLogger(MantCorrelativo.class
			.getName());

	public static Boolean insertarCorrelativo(Correlativo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdCorrelativo() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = insertarCorrelativo(bean, pm);
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

	public static Boolean insertarCorrelativo(Correlativo bean,
			PersistenceManager pm) throws UnknownException {
		Date fechaServer = new Date();
		bean.setVersion(fechaServer.getTime());
		CorrelativoActiva beanActiva = new CorrelativoActiva();
		beanActiva.setIdCorrelativoActiva(bean.getCodeCorrelativo());
		beanActiva.setCodeCorrelativo(bean.getCodeCorrelativo());
		beanActiva.setFechaIni(new Date());
		beanActiva.setEstado(1);
		beanActiva.setVersion(fechaServer.getTime());
		beanActiva.setBeanCorrelativo(bean);
		bean.setCodeCorrelativoActivaActual(beanActiva.getIdCorrelativoActiva());
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicCorrelativo logic = new LogicCorrelativo(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		if (resultado) {
			LogicCorrelativoActiva logicActiva = new LogicCorrelativoActiva(pm);
			parametro.setBean(beanActiva);
			parametro.setTipoOperacion("I");
			logicActiva.mantenimiento(parametro);
		}
		return resultado;
	}

	public static Boolean actualizarCorrelativo(Correlativo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdCorrelativo() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado = false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();
				LogicCorrelativo logic = new LogicCorrelativo(pm);
				parametro.setTipoOperacion(bean.getOperacion());
				parametro.setBean(bean);
				parametro.setTipoOperacion(bean.getOperacion());
				resultado = logic.mantenimiento(parametro);
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

	public static Boolean eliminarCorrelativo(Correlativo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdCorrelativo() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = eliminarCorrelativo(bean, pm);
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

	public static Boolean eliminarCorrelativo(Correlativo bean,
			PersistenceManager pm) throws UnknownException {
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(bean.getIdCorrelativo());
		parametro.setTipoOperacion(bean.getOperacion());
		LogicCorrelativo logic = new LogicCorrelativo(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<Correlativo> listarCorrelativo() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Correlativo.class, "CorrelativoGroup")
					.addMembers("beanTipoDocumento");// , "listActiva"
			pm.getFetchPlan().addGroup("CorrelativoGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicCorrelativo logic = new LogicCorrelativo(pm);
			List<Correlativo> resultado = (List<Correlativo>) pm
					.detachCopyAll(logic.getListarBean());
			tx.commit();
			pm.close();
			return resultado;
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

	}

	public static List<Correlativo> listarCorrelativo(String codeTipoDoc)
			throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Correlativo.class, "CorrelativoGroup")
					.addMember("beanTipoDocumento");
			pm.getFetchPlan().addGroup("CorrelativoGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicCorrelativo logic = new LogicCorrelativo(pm);
			List<Correlativo> resultado = (List<Correlativo>) pm
					.detachCopyAll(logic.getListarBean(codeTipoDoc));
			tx.commit();
			pm.close();
			return resultado;
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

	}

}
