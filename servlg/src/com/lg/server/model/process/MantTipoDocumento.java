package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;
import com.lg.server.model.beans.TipoDocumento;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicTipoDocumento;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantTipoDocumento {

	private static final Logger LOG = Logger.getLogger(MantFamilia.class
			.getName());

	public static Boolean insertarTipoDocumento(TipoDocumento bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdTipoDoc() != null) {
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
				LogicTipoDocumento logic = new LogicTipoDocumento(pm);
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

	public static Boolean actualizarTipoDocumento(TipoDocumento bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdTipoDoc() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer = new Date();
				bean.setVersion(fechaServer.getTime());
				BeanParametro parametro = new BeanParametro();
				LogicTipoDocumento logic = new LogicTipoDocumento(pm);
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

	public static Boolean eliminarTipoDocumento(TipoDocumento bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdTipoDoc() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(bean);
				parametro.setId(bean.getIdTipoDoc());
				parametro.setTipoOperacion(bean.getOperacion());
				LogicTipoDocumento logic = new LogicTipoDocumento(pm);
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

	public static List<TipoDocumento> listarTipoDocumento()
			throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicTipoDocumento logic = new LogicTipoDocumento(pm);
			List<TipoDocumento> resultado = (List<TipoDocumento>) logic
					.getListarBean();
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
