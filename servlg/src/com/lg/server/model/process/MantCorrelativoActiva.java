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

public class MantCorrelativoActiva {
	private static final Logger LOG = Logger
			.getLogger(MantCorrelativoActiva.class.getName());

	public static Boolean insertarCorrelativoActiva(CorrelativoActiva bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdCorrelativoActiva() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = insertarCorrelativoActiva(bean, pm);
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

	public static Boolean insertarCorrelativoActiva(CorrelativoActiva bean,
			PersistenceManager pm) throws UnknownException {
		Date fechaServer = new Date();
		LogicCorrelativo logicCorrelativo = new LogicCorrelativo(pm);
		Correlativo beanCorrelativo = (Correlativo) logicCorrelativo
				.getBean(bean.getCodeCorrelativo());
		beanCorrelativo.setEstadoActual(1);
		beanCorrelativo.setVersion(fechaServer.getTime());
		beanCorrelativo.setCodeCorrelativoActivaActual(bean
				.getIdCorrelativoActiva());
		bean.setVersion(fechaServer.getTime());
		bean.setFechaIni(new Date());
		bean.setEstado(1);
		bean.setBeanCorrelativo(beanCorrelativo);
		bean.setCodeCorrelativo(beanCorrelativo.getIdCorrelativo());
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicCorrelativoActiva logic = new LogicCorrelativoActiva(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		if (resultado) {
			parametro.setBean(beanCorrelativo);
			parametro.setTipoOperacion("A");
			resultado = logicCorrelativo.mantenimiento(parametro);
		}
		return resultado;
	}

	public static Boolean actualizarCorrelativoActiva(CorrelativoActiva bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdCorrelativoActiva() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado = false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				BeanParametro parametro = new BeanParametro();
				LogicCorrelativoActiva logic = new LogicCorrelativoActiva(pm);
				CorrelativoActiva beanAux = (CorrelativoActiva) logic
						.getBean(bean.getIdCorrelativoActiva());
				if (beanAux.getBeanCorrelativo().getIdCorrelativo()
						.equalsIgnoreCase(bean.getCodeCorrelativo())) {
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setFechaFin(new Date());
					beanAux.setEstado(0);
					beanAux.getBeanCorrelativo().setEstadoActual(0);
					parametro.setBean(beanAux);
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
					if (resultado) {
						LogicCorrelativo logicCorrelativo = new LogicCorrelativo(
								pm);
						Correlativo beanCorrelativo = (Correlativo) logicCorrelativo
								.getBean(bean.getCodeCorrelativo());
						beanCorrelativo.setEstadoActual(0);
						beanCorrelativo.setVersion(fechaServer.getTime());
						beanCorrelativo.setCodeCorrelativoActivaActual(null);
						parametro.setBean(beanCorrelativo);
						parametro.setTipoOperacion("A");
						resultado = logicCorrelativo.mantenimiento(parametro);
					}
				}
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

	public static List<CorrelativoActiva> listarCorrelativoActiva()
			throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF()
					.getFetchGroup(CorrelativoActiva.class,
							"CorrelativoActivaGroup")
					.addMember("beanCorrelativo");
			pm.getFetchPlan().addGroup("CorrelativoActivaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicCorrelativoActiva logic = new LogicCorrelativoActiva(pm);
			List<CorrelativoActiva> resultado = (List<CorrelativoActiva>) pm
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

	public static List<CorrelativoActiva> listarCorrelativoActiva(
			String codeCorrelativo) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF()
					.getFetchGroup(CorrelativoActiva.class,
							"CorrelativoActivaGroup")
					.addMember("beanCorrelativo");
			pm.getFetchPlan().addGroup("CorrelativoActivaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicCorrelativoActiva logic = new LogicCorrelativoActiva(pm);
			List<CorrelativoActiva> resultado = (List<CorrelativoActiva>) pm
					.detachCopyAll(logic.getListarBean(codeCorrelativo));
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
