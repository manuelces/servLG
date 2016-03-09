package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.KeyFactory;
import com.lg.server.model.beans.Familia;
import com.lg.server.model.beans.SubFamilia;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicFamilia;
import com.lg.server.model.logic.LogicSubFamilia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantSubFamilia {
	private static final Logger LOG = Logger.getLogger(MantSubFamilia.class
			.getName());

	public static Boolean insertarSubFamilia(SubFamilia bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdSubFamilia() != null) {					
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();	
				Boolean resultado=insertarSubFamilia(bean,pm);
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
	
	public static Boolean insertarSubFamilia(SubFamilia bean,PersistenceManager pm)throws UnknownException{
		Date fechaServer=new Date();
		LogicFamilia logicFamilia=new LogicFamilia(pm);
		Familia beanFamilia=(Familia)logicFamilia.getBean(bean.getCodeFamilia());
		bean.setVersion(fechaServer.getTime());
		bean.setBeanFamilia(beanFamilia);	
		beanFamilia.getListSubFamilia().add(bean);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicSubFamilia logic = new LogicSubFamilia(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static Boolean actualizarSubFamilia(SubFamilia bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdSubFamilia() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado=false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();				
				BeanParametro parametro = new BeanParametro();				
				LogicSubFamilia logic = new LogicSubFamilia(pm);
				SubFamilia beanAux=(SubFamilia)logic.getBean(bean.getIdSubFamilia());
				if(beanAux.getBeanFamilia().getIdFamilia().equalsIgnoreCase(bean.getBeanFamilia().getIdFamilia())){
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setDescripcion(bean.getDescripcion());
					parametro.setBean(beanAux);
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
				}else{					
					bean.setOperacion("E");
					resultado = eliminarSubFamilia(bean,pm);
					if(resultado){
						bean.setIdSubFamilia(bean.getBeanFamilia().getIdFamilia());
						bean.setOperacion("I");
						resultado = insertarSubFamilia(bean,pm);
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

	public static Boolean eliminarSubFamilia(SubFamilia bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") 
				&& bean.getIdSubFamilia() != null) {							
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();				
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado=eliminarSubFamilia(bean,pm);
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
	
	public static Boolean eliminarSubFamilia(SubFamilia bean,PersistenceManager pm)throws UnknownException{
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(KeyFactory.stringToKey(bean.getIdSubFamilia()));
		parametro.setTipoOperacion(bean.getOperacion());
		LogicSubFamilia logic = new LogicSubFamilia(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<SubFamilia> listarSubFamilia() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(SubFamilia.class, "SubFamiliaGroup").addMember("beanFamilia");
			pm.getFetchPlan().addGroup("SubFamiliaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicSubFamilia logic = new LogicSubFamilia(pm);
			List<SubFamilia> resultado = (List<SubFamilia>) pm.detachCopyAll( logic.getListarBean());
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
	
	
	public static List<SubFamilia> listarSubFamilia(String codeFamilia) throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(SubFamilia.class, "SubFamiliaGroup").addMember("beanFamilia");
			pm.getFetchPlan().addGroup("SubFamiliaGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicSubFamilia logic = new LogicSubFamilia(pm);
			List<SubFamilia> resultado = (List<SubFamilia>) pm.detachCopyAll( logic.getListarBean(codeFamilia));
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
