package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Trabajador;
import com.lg.server.model.beans.UsuarioActiva;
import com.lg.server.model.beans.Usuario;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicTrabajador;
import com.lg.server.model.logic.LogicUsuario;
import com.lg.server.model.logic.LogicUsuarioActiva;
import com.lg.shared.AESencrypt;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantUsuario {
	private static final Logger LOG = Logger.getLogger(MantUsuario.class
			.getName());

	public static Boolean insertarUsuario(Usuario bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdUsuario() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				String clave = bean.getClave();
				String encode = AESencrypt.encrypt(clave);
				bean.setClave(encode);
				Boolean resultado = insertarUsuario(bean, pm);
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

	public static Boolean insertarUsuario(Usuario bean, PersistenceManager pm)
			throws UnknownException {
		Date fechaServer = new Date();
		bean.setVersion(fechaServer.getTime());
		UsuarioActiva beanActiva = new UsuarioActiva();
		beanActiva.setIdUsuarioActiva(bean.getCodeUsuario());
		beanActiva.setCodeUsuario(bean.getCodeUsuario());
		beanActiva.setFechaIni(new Date());
		beanActiva.setEstado(1);
		beanActiva.setVersion(fechaServer.getTime());
		beanActiva.setBeanUsuario(bean);
		bean.setCodeUsuarioActivaActual(beanActiva.getIdUsuarioActiva());
		LogicTrabajador logicTrabajador = new LogicTrabajador(pm);
		Trabajador beanTrabajador = (Trabajador) logicTrabajador.getBean(bean
				.getCodeTrabajador());
		bean.setBeanTrabajador(beanTrabajador);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicTrabajador logic = new LogicTrabajador(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		if (resultado) {
			LogicUsuarioActiva logicActiva = new LogicUsuarioActiva(pm);
			parametro.setBean(beanActiva);
			parametro.setTipoOperacion("I");
			logicActiva.mantenimiento(parametro);
		}
		return resultado;
	}

	/*
	 * public static Boolean actualizarUsuario(Usuario bean) throws
	 * UnknownException { if (bean.getOperacion().equalsIgnoreCase("A") &&
	 * bean.getIdUsuario() != null) { PersistenceManager pm = null; Transaction
	 * tx = null; Boolean resultado = false; try { pm =
	 * PMF.getPMF().getPersistenceManager(); tx = pm.currentTransaction();
	 * tx.begin(); Date fechaServer = new Date(); BeanParametro parametro = new
	 * BeanParametro(); LogicUsuario logic = new LogicUsuario(pm); Usuario
	 * beanAux = (Usuario) logic.getBean(bean.getIdUsuario()); if (beanAux
	 * .getBeanTrabajador() .getIdTrabajador() .equalsIgnoreCase(
	 * bean.getBeanTrabajador().getIdTrabajador())) {
	 * beanAux.setVersion(fechaServer.getTime());
	 * beanAux.setLogin(bean.getLogin()); beanAux.setClave(bean.getClave());
	 * parametro.setBean(beanAux);
	 * parametro.setTipoOperacion(bean.getOperacion()); resultado =
	 * logic.mantenimiento(parametro); } else { bean.setOperacion("E");
	 * resultado = eliminarUsuario(bean, pm); if (resultado) {
	 * bean.setIdUsuario(bean.getBeanTrabajador() .getIdTrabajador());
	 * bean.setOperacion("I"); resultado = insertarUsuario(bean, pm); } } if
	 * (resultado) { tx.commit(); pm.close(); return true; } else {
	 * tx.rollback(); pm.close(); return false; } } catch (Exception ex) {
	 * LOG.warning(ex.getMessage()); LOG.info(ex.getLocalizedMessage()); throw
	 * new UnknownException(ex.getMessage()); } finally { if (!pm.isClosed()) {
	 * if (tx.isActive()) { tx.rollback(); } pm.close(); } } } else { throw new
	 * UnknownException("Verifique Catalogo de Servicio"); } }
	 */

	public static Boolean eliminarUsuario(Usuario bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdUsuario() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = eliminarUsuario(bean, pm);
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

	public static Boolean eliminarUsuario(Usuario bean, PersistenceManager pm)
			throws UnknownException {
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(bean.getIdUsuario());
		parametro.setTipoOperacion(bean.getOperacion());
		LogicTrabajador logic = new LogicTrabajador(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<Usuario> listarUsuario() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Usuario.class, "UsuarioGroup")
					.addMember("beanTrabajador");
			pm.getFetchPlan().addGroup("UsuarioGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicUsuario logic = new LogicUsuario(pm);
			List<Usuario> resultado = (List<Usuario>) pm.detachCopyAll(logic
					.getListarBean());
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

	/*
	 * public static List<Trabajador> listarTrabajador(String
	 * codeTipoTrabajador) throws UnknownException {
	 * 
	 * PersistenceManager pm = null; Transaction tx = null; try { pm =
	 * PMF.getPMF().getPersistenceManager();
	 * PMF.getPMF().getFetchGroup(Trabajador.class, "TrabajadorGroup")
	 * .addMember("beanFamilia"); pm.getFetchPlan().addGroup("TrabajadorGroup");
	 * pm.getFetchPlan().setMaxFetchDepth(1); tx = pm.currentTransaction();
	 * tx.begin(); pm.setDetachAllOnCommit(true); LogicTrabajador logic = new
	 * LogicTrabajador(pm); List<Trabajador> resultado = (List<Trabajador>) pm
	 * .detachCopyAll(logic.getListarBean(codeTipoTrabajador)); tx.commit();
	 * pm.close(); return resultado; } catch (Exception ex) {
	 * LOG.warning(ex.getMessage()); LOG.info(ex.getLocalizedMessage()); throw
	 * new UnknownException(ex.getMessage()); } finally { if (!pm.isClosed()) {
	 * if (tx.isActive()) { tx.rollback(); } pm.close(); } }
	 * 
	 * }
	 */
}
