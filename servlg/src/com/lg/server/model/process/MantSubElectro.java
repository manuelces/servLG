package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.Electro;
import com.lg.server.model.beans.SubElectro;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicElectro;
import com.lg.server.model.logic.LogicSubElectro;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantSubElectro {
	private static final Logger LOG = Logger.getLogger(MantSubElectro.class
			.getName());

	public static Boolean insertarSubElectro(SubElectro bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdSubElectro() != null) {					
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();	
				Boolean resultado=insertarSubElectro(bean,pm);
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
	
	public static Boolean insertarSubElectro(SubElectro bean,PersistenceManager pm)throws UnknownException{
		Date fechaServer=new Date();
		LogicElectro logicElectro=new LogicElectro(pm);
		Electro beanElectro=(Electro)logicElectro.getBean(bean.getCodeElectro());
		bean.setVersion(fechaServer.getTime());
		bean.setBeanElectro(beanElectro);	
		beanElectro.getListSubElectro().add(bean);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicSubElectro logic = new LogicSubElectro(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static Boolean actualizarSubElectro(SubElectro bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdSubElectro() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado=false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();				
				BeanParametro parametro = new BeanParametro();				
				LogicSubElectro logic = new LogicSubElectro(pm);
				SubElectro beanAux=(SubElectro)logic.getBean(bean.getIdSubElectro());
				if(beanAux.getBeanElectro().getIdElectro().equalsIgnoreCase(bean.getBeanElectro().getIdElectro())){
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setDescripcion(bean.getDescripcion());
					parametro.setBean(beanAux);
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
				}else{					
					bean.setOperacion("E");
					resultado = eliminarSubElectro(bean,pm);
					if(resultado){
						bean.setIdSubElectro(bean.getBeanElectro().getIdElectro());
						bean.setOperacion("I");
						resultado = insertarSubElectro(bean,pm);
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

	public static Boolean eliminarSubElectro(SubElectro bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") 
				&& bean.getIdSubElectro() != null) {							
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();				
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado=eliminarSubElectro(bean,pm);
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
	
	public static Boolean eliminarSubElectro(SubElectro bean,PersistenceManager pm)throws UnknownException{
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(KeyFactory.stringToKey(bean.getIdSubElectro()));
		parametro.setTipoOperacion(bean.getOperacion());
		LogicSubElectro logic = new LogicSubElectro(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<SubElectro> listarSubElectro() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(SubElectro.class, "SubElectroGroup").addMember("beanElectro");
			pm.getFetchPlan().addGroup("SubElectroGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicSubElectro logic = new LogicSubElectro(pm);
			List<SubElectro> resultado = (List<SubElectro>) pm.detachCopyAll( logic.getListarBean());
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
	
	
	public static List<SubElectro> listarSubElectro(String codeElectro) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(SubElectro.class, "SubElectroGroup").addMember("beanElectro");
			pm.getFetchPlan().addGroup("SubElectroGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicSubElectro logic = new LogicSubElectro(pm);
			List<SubElectro> resultado = (List<SubElectro>) pm.detachCopyAll( logic.getListarBean(codeElectro));
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
