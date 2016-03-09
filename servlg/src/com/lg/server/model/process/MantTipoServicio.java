package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import com.lg.server.model.beans.EmpresaFabricante;
import com.lg.server.model.beans.TipoServicio;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicEmpresaFabricante;
import com.lg.server.model.logic.LogicSubFamilia;
import com.lg.server.model.logic.LogicTipoServicio;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantTipoServicio {
	private static final Logger LOG = Logger.getLogger(MantTipoServicio.class
			.getName());

	public static Boolean insertarTipoServicio(TipoServicio bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdTipoServicio() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = insertarTipoServicio(bean, pm);
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

	public static Boolean insertarTipoServicio(TipoServicio bean,
			PersistenceManager pm) throws UnknownException {
		Date fechaServer = new Date();
		LogicEmpresaFabricante logicEmpresaFabricante = new LogicEmpresaFabricante(
				pm);
		EmpresaFabricante beanEmpresaFabricante = (EmpresaFabricante) logicEmpresaFabricante
				.getBean(bean.getCodeEmpresaFabricante());
		bean.setVersion(fechaServer.getTime());
		bean.setBeanEmpresaFabricante(beanEmpresaFabricante);
		beanEmpresaFabricante.getListTipoServicio().add(bean);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicTipoServicio logic = new LogicTipoServicio(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static Boolean actualizarTipoServicio(TipoServicio bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdTipoServicio() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado = false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				BeanParametro parametro = new BeanParametro();
				LogicTipoServicio logic = new LogicTipoServicio(pm);
				TipoServicio beanAux = (TipoServicio) logic.getBean(bean
						.getIdTipoServicio());
				if (beanAux
						.getBeanEmpresaFabricante()
						.getIdEmpresaFabricante()
						.equalsIgnoreCase(
								bean.getBeanEmpresaFabricante()
										.getIdEmpresaFabricante())) {
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setDescripcion(bean.getDescripcion());
					beanAux.setAbreviatura(bean.getAbreviatura());
					parametro.setBean(beanAux);
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
				} else {
					bean.setOperacion("E");
					resultado = eliminarTipoServicio(bean, pm);
					if (resultado) {
						bean.setIdTipoServicio(bean.getBeanEmpresaFabricante()
								.getIdEmpresaFabricante());
						bean.setOperacion("I");
						resultado = insertarTipoServicio(bean, pm);
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

	public static Boolean eliminarTipoServicio(TipoServicio bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdTipoServicio() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = eliminarTipoServicio(bean, pm);
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

	public static Boolean eliminarTipoServicio(TipoServicio bean,
			PersistenceManager pm) throws UnknownException {
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(KeyFactory.stringToKey(bean.getIdTipoServicio()));
		parametro.setTipoOperacion(bean.getOperacion());
		LogicSubFamilia logic = new LogicSubFamilia(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<TipoServicio> listarTipoServicio()
			throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(TipoServicio.class, "TipoServicioGroup")
					.addMember("beanEmpresaFabricante");
			pm.getFetchPlan().addGroup("TipoServicioGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicTipoServicio logic = new LogicTipoServicio(pm);
			List<TipoServicio> resultado = (List<TipoServicio>) pm
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

	public static List<TipoServicio> listarTipoServicio(
			String codeEmpresaFabricante) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(TipoServicio.class, "TipoServicioGroup")
					.addMember("beanEmpresaFabricante");
			pm.getFetchPlan().addGroup("TipoServicioGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicTipoServicio logic = new LogicTipoServicio(pm);
			List<TipoServicio> resultado = (List<TipoServicio>) pm
					.detachCopyAll(logic.getListarBean(codeEmpresaFabricante));
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
