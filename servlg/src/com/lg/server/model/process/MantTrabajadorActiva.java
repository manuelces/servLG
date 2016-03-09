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

public class MantTrabajadorActiva {
	private static final Logger LOG = Logger.getLogger(MantTrabajadorActiva.class
			.getName());

	public static Boolean insertarTrabajadorActiva(TrabajadorActiva bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdTrabajadorActiva() != null) {					
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();	
				Boolean resultado=insertarTrabajadorActiva(bean,pm);
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
	
	public static Boolean insertarTrabajadorActiva(TrabajadorActiva bean,PersistenceManager pm)throws UnknownException{
		Date fechaServer=new Date();
		LogicTrabajador logicTrabajador=new LogicTrabajador(pm);
		Trabajador beanTrabajador=(Trabajador)logicTrabajador.getBean(bean.getCodeTrabajador());
		beanTrabajador.setEstadoActual(1);
		beanTrabajador.setVersion(fechaServer.getTime());
		beanTrabajador.setCodeTrabajadorActivaActual(bean.getIdTrabajadorActiva());
		bean.setVersion(fechaServer.getTime());
		bean.setFechaIni(new Date());
		bean.setEstado(1);
		bean.setBeanTrabajador(beanTrabajador);	
		bean.setCodeTrabajador(beanTrabajador.getIdTrabajador());
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());		
		LogicTrabajadorActiva logic = new LogicTrabajadorActiva(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		if(resultado){
			parametro.setBean(beanTrabajador);
			parametro.setTipoOperacion("A");
			resultado=logicTrabajador.mantenimiento(parametro);
		}
		return resultado;
	}

	public static Boolean actualizarTrabajadorActiva(TrabajadorActiva bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdTrabajadorActiva() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado=false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();				
				BeanParametro parametro = new BeanParametro();				
				LogicTrabajadorActiva logic = new LogicTrabajadorActiva(pm);
				TrabajadorActiva beanAux=(TrabajadorActiva)logic.getBean(bean.getIdTrabajadorActiva());
				if(beanAux.getBeanTrabajador().getIdTrabajador().equalsIgnoreCase(bean.getCodeTrabajador())){
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setFechaFin(new Date());
					beanAux.setEstado(0);
					beanAux.getBeanTrabajador().setEstadoActual(0);
					parametro.setBean(beanAux);					
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
					if(resultado){
						LogicTrabajador logicTrabajador=new LogicTrabajador(pm);
						Trabajador beanTrabajador=(Trabajador)logicTrabajador.getBean(bean.getCodeTrabajador());
						beanTrabajador.setEstadoActual(0);
						beanTrabajador.setVersion(fechaServer.getTime());
						beanTrabajador.setCodeTrabajadorActivaActual(null);
						parametro.setBean(beanTrabajador);					
						parametro.setTipoOperacion("A");
						resultado=logicTrabajador.mantenimiento(parametro);
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

	public static List<TrabajadorActiva> listarTrabajadorActiva() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(TrabajadorActiva.class, "TrabajadorActivaGroup").addMember("beanTrabajador");
			pm.getFetchPlan().addGroup("TrabajadorActivaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicTrabajadorActiva logic = new LogicTrabajadorActiva(pm);
			List<TrabajadorActiva> resultado = (List<TrabajadorActiva>) pm.detachCopyAll( logic.getListarBean());
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
	
	
	public static List<TrabajadorActiva> listarTrabajadorActiva(String codeTrabajador) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(TrabajadorActiva.class, "TrabajadorActivaGroup").addMember("beanTrabajador");
			pm.getFetchPlan().addGroup("TrabajadorActivaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicTrabajadorActiva logic = new LogicTrabajadorActiva(pm);
			List<TrabajadorActiva> resultado = (List<TrabajadorActiva>) pm.detachCopyAll( logic.getListarBean(codeTrabajador));
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
