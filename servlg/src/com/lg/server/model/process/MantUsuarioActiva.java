package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Usuario;
import com.lg.server.model.beans.UsuarioActiva;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicUsuario;
import com.lg.server.model.logic.LogicUsuarioActiva;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantUsuarioActiva {
	private static final Logger LOG = Logger.getLogger(MantUsuarioActiva.class
			.getName());

	public static Boolean insertarUsuarioActiva(UsuarioActiva bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdUsuarioActiva() != null) {					
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();	
				Boolean resultado=insertarUsuarioActiva(bean,pm);
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
	
	public static Boolean insertarUsuarioActiva(UsuarioActiva bean,PersistenceManager pm)throws UnknownException{
		Date fechaServer=new Date();
		LogicUsuario logicUsuario=new LogicUsuario(pm);
		Usuario beanUsuario=(Usuario)logicUsuario.getBean(bean.getCodeUsuario());
		beanUsuario.setEstadoActual(1);
		beanUsuario.setVersion(fechaServer.getTime());
		beanUsuario.setCodeUsuarioActivaActual(bean.getIdUsuarioActiva());
		bean.setVersion(fechaServer.getTime());
		bean.setFechaIni(new Date());
		bean.setEstado(1);
		bean.setBeanUsuario(beanUsuario);	
		bean.setCodeUsuario(beanUsuario.getIdUsuario());
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());		
		LogicUsuarioActiva logic = new LogicUsuarioActiva(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		if(resultado){
			parametro.setBean(beanUsuario);
			parametro.setTipoOperacion("A");
			resultado=logicUsuario.mantenimiento(parametro);
		}
		return resultado;
	}

	public static Boolean actualizarUsuarioActiva(UsuarioActiva bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdUsuarioActiva() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado=false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();				
				BeanParametro parametro = new BeanParametro();				
				LogicUsuarioActiva logic = new LogicUsuarioActiva(pm);
				UsuarioActiva beanAux=(UsuarioActiva)logic.getBean(bean.getIdUsuarioActiva());
				if(beanAux.getBeanUsuario().getIdUsuario().equalsIgnoreCase(bean.getCodeUsuario())){
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setFechaFin(new Date());
					beanAux.setEstado(0);
					beanAux.getBeanUsuario().setEstadoActual(0);
					parametro.setBean(beanAux);					
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
					if(resultado){
						LogicUsuario logicUsuario=new LogicUsuario(pm);
						Usuario beanUsuario=(Usuario)logicUsuario.getBean(bean.getCodeUsuario());
						beanUsuario.setEstadoActual(0);
						beanUsuario.setVersion(fechaServer.getTime());
						beanUsuario.setCodeUsuarioActivaActual(null);
						parametro.setBean(beanUsuario);					
						parametro.setTipoOperacion("A");
						resultado=logicUsuario.mantenimiento(parametro);
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

	public static List<UsuarioActiva> listarUsuarioActiva() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(UsuarioActiva.class, "UsuarioActivaGroup").addMember("beanUsuario");
			pm.getFetchPlan().addGroup("UsuarioActivaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicUsuarioActiva logic = new LogicUsuarioActiva(pm);
			List<UsuarioActiva> resultado = (List<UsuarioActiva>) pm.detachCopyAll( logic.getListarBean());
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
	
	
	public static List<UsuarioActiva> listarUsuarioActiva(String codeUsuario) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(UsuarioActiva.class, "UsuarioActivaGroup").addMember("beanUsuario");
			pm.getFetchPlan().addGroup("UsuarioActivaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicUsuarioActiva logic = new LogicUsuarioActiva(pm);
			List<UsuarioActiva> resultado = (List<UsuarioActiva>) pm.detachCopyAll( logic.getListarBean(codeUsuario));
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
