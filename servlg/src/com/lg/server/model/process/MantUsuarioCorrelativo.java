package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Correlativo;
import com.lg.server.model.beans.Usuario;
import com.lg.server.model.beans.UsuarioCorrelativo;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicCorrelativo;
import com.lg.server.model.logic.LogicUsuario;
import com.lg.server.model.logic.LogicUsuarioCorrelativo;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantUsuarioCorrelativo {

	private static final Logger LOG = Logger
			.getLogger(MantUsuarioCorrelativo.class.getName());

	public static Boolean insertarUsuarioCorrelativo(UsuarioCorrelativo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdUsuarioCorrelativo() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = insertarUsuarioCorrelativo(bean, pm);
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

	public static Boolean insertarUsuarioCorrelativo(UsuarioCorrelativo bean,
			PersistenceManager pm) throws UnknownException {
		Date fechaServer = new Date();
		bean.setVersion(fechaServer.getTime());
		LogicUsuario logicUsuario = new LogicUsuario(pm);
		Usuario beanUsuario = (Usuario) logicUsuario.getBean(bean
				.getCodeUsuario());
		bean.setBeanUsuario(beanUsuario);
		LogicCorrelativo logicCorrelativo = new LogicCorrelativo(pm);
		Correlativo beanCorrelativo = (Correlativo) logicCorrelativo
				.getBean(bean.getCodeCorrelativo());
		bean.setBeanCorrelativo(beanCorrelativo);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static Boolean actualizarUsuarioCorrelativo(UsuarioCorrelativo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdUsuarioCorrelativo() != null) {
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
				LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);
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

	public static Boolean eliminarUsuarioCorrelativo(UsuarioCorrelativo bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdUsuarioCorrelativo() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = eliminarUsuarioCorrelativo(bean, pm);
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

	public static Boolean eliminarUsuarioCorrelativo(UsuarioCorrelativo bean,
			PersistenceManager pm) throws UnknownException {
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(bean.getIdUsuarioCorrelativo());
		parametro.setTipoOperacion(bean.getOperacion());
		LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<UsuarioCorrelativo> listarUsuarioCorrelativo()
			throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF()
					.getFetchGroup(UsuarioCorrelativo.class,
							"UsuarioCorrelativoGroup")
					.addMembers("beanUsuario", "beanCorrelativo");// ,
																	// "listActiva"
			pm.getFetchPlan().addGroup("UsuarioCorrelativoGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicUsuarioCorrelativo logic = new LogicUsuarioCorrelativo(pm);
			List<UsuarioCorrelativo> resultado = (List<UsuarioCorrelativo>) pm
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

}
