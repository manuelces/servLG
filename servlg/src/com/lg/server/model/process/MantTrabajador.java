package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Trabajador;
import com.lg.server.model.beans.TrabajadorActiva;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicTrabajador;
import com.lg.server.model.logic.LogicTrabajadorActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantTrabajador {
	private static final Logger LOG = Logger.getLogger(MantTrabajador.class
			.getName());

	public static Boolean insertarTrabajador(Trabajador bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdTrabajador() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = insertarTrabajador(bean, pm);
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

	public static Boolean insertarTrabajador(Trabajador bean,
			PersistenceManager pm) throws UnknownException {
		Date fechaServer = new Date();	
		bean.setVersion(fechaServer.getTime());		
		TrabajadorActiva beanActiva = new TrabajadorActiva();
		beanActiva.setIdTrabajadorActiva(bean.getCodeTrabajador());
		beanActiva.setCodeTrabajador(bean.getCodeTrabajador());
		beanActiva.setFechaIni(new Date());
		beanActiva.setEstado(1);
		beanActiva.setVersion(fechaServer.getTime());
		beanActiva.setBeanTrabajador(bean);
		bean.setCodeTrabajadorActivaActual(beanActiva.getIdTrabajadorActiva());
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicTrabajador logic = new LogicTrabajador(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		if(resultado){
			LogicTrabajadorActiva logicActiva=new LogicTrabajadorActiva(pm);			
			parametro.setBean(beanActiva);
			parametro.setTipoOperacion("I");
			logicActiva.mantenimiento(parametro);
		}
		return resultado;
	}

	public static Boolean actualizarTrabajador(Trabajador bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdTrabajador() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado = false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				BeanParametro parametro = new BeanParametro();
				LogicTrabajador logic = new LogicTrabajador(pm);
				Trabajador beanAux = (Trabajador) logic.getBean(bean
						.getIdTrabajador());
				// if
				// (beanAux.getBeanTipoTrabajador().getIdTipoTrabajador().equalsIgnoreCase(bean.getBeanTipoTrabajador().getIdTipoTrabajador()))
				// {
				beanAux.setVersion(fechaServer.getTime());
				beanAux.setPaterno(bean.getPaterno());
				beanAux.setMaterno(bean.getMaterno());
				beanAux.setNombre(bean.getNombre());
				beanAux.setDni(bean.getDni());
				beanAux.setBeanTipoTrabajador(bean.getBeanTipoTrabajador());
				beanAux.setCodeTipoTrabajador(bean.getCodeTipoTrabajador());
				parametro.setBean(beanAux);
				parametro.setTipoOperacion(bean.getOperacion());
				resultado = logic.mantenimiento(parametro);
				/*
				 * } else { bean.setOperacion("E"); resultado =
				 * eliminarTrabajador(bean, pm); if (resultado) {
				 * bean.setIdTrabajador(bean.getBeanTipoTrabajador()
				 * .getIdTipoTrabajador()); bean.setOperacion("I"); resultado =
				 * insertarTrabajador(bean, pm); } }
				 */
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

	public static Boolean eliminarTrabajador(Trabajador bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdTrabajador() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = eliminarTrabajador(bean, pm);
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

	public static Boolean eliminarTrabajador(Trabajador bean,
			PersistenceManager pm) throws UnknownException {
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(bean.getIdTrabajador());
		parametro.setTipoOperacion(bean.getOperacion());
		LogicTrabajador logic = new LogicTrabajador(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<Trabajador> listarTrabajador() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Trabajador.class, "TrabajadorGroup")
					.addMembers("beanTipoTrabajador");//, "listActiva"
			pm.getFetchPlan().addGroup("TrabajadorGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicTrabajador logic = new LogicTrabajador(pm);
			List<Trabajador> resultado = (List<Trabajador>) pm
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

	/*public static List<Trabajador> listarTrabajador(String codeTipoTrabajador)
			throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Trabajador.class, "TrabajadorGroup")
					.addMember("beanFamilia");
			pm.getFetchPlan().addGroup("TrabajadorGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicTrabajador logic = new LogicTrabajador(pm);
			List<Trabajador> resultado = (List<Trabajador>) pm
					.detachCopyAll(logic.getListarBean(codeTipoTrabajador));
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

	}*/
}
