package com.lg.server.model.process;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Departamento;
import com.lg.server.model.beans.Pais;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicDepartamento;
import com.lg.server.model.logic.LogicPais;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantDepartamento {
	private static final Logger LOG = Logger.getLogger(MantDepartamento.class
			.getName());

	public static Boolean insertarDepartamento(Departamento bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdDepartamento() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();				
				Boolean resultado = insertarDepartamento(bean,pm);				
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
	
	public static Boolean insertarDepartamento(Departamento bean,PersistenceManager pm)throws UnknownException{
		Date fechaServer=new Date();
		LogicPais logicPais=new LogicPais(pm);
		Pais beanPais=(Pais)logicPais.getBean(bean.getCodePais());
		bean.setVersion(fechaServer.getTime());
		bean.setBeanPais(beanPais);	
		beanPais.getListDepartamento().add(bean);
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicDepartamento logic = new LogicDepartamento(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}


	public static Boolean actualizarDepartamento(Departamento bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdDepartamento() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado=false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();				
				BeanParametro parametro = new BeanParametro();				
				LogicDepartamento logic = new LogicDepartamento(pm);
				Departamento beanAux=(Departamento)logic.getBean(bean.getIdDepartamento());
				if(beanAux.getBeanPais().getIdPais().equalsIgnoreCase(bean.getBeanPais().getIdPais())){
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setCodigo(bean.getCodigo());
					beanAux.setDescripcion(bean.getDescripcion());
					parametro.setBean(beanAux);
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
				}else{					
					bean.setOperacion("E");
					resultado = eliminarDepartamento(bean,pm);
					if(resultado){
						bean.setIdDepartamento(bean.getBeanPais().getIdPais());
						bean.setOperacion("I");
						resultado = insertarDepartamento(bean,pm);
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

	public static Boolean eliminarDepartamento(Departamento bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E")
				&& bean.getIdDepartamento() != null) {
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado = eliminarDepartamento(bean,pm);				
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
	
	public static Boolean eliminarDepartamento(Departamento bean,PersistenceManager pm)throws UnknownException{
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(bean.getIdDepartamento());
		parametro.setTipoOperacion(bean.getOperacion());
		LogicDepartamento logic = new LogicDepartamento(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}
	

	public static List<Departamento> listarDepartamento() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Departamento.class, "DepartamentoGroup")
					.addMember("beanPais");
			pm.getFetchPlan().addGroup("DepartamentoGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicDepartamento logic = new LogicDepartamento(pm);
			List<Departamento> resultado = (List<Departamento>) pm
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

	public static List<Departamento> listarDepartamento(String codePais)
			throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			PMF.getPMF().getFetchGroup(Departamento.class, "DepartamentoGroup")
					.addMember("beanPais");
			pm.getFetchPlan().addGroup("DepartamentoGroup");
			pm.getFetchPlan().setMaxFetchDepth(1);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicDepartamento logic = new LogicDepartamento(pm);
			List<Departamento> resultado = (List<Departamento>) pm
					.detachCopyAll(logic.getListarBean(codePais));
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
