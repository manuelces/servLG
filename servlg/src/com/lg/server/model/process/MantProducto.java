package com.lg.server.model.process;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.lg.server.model.beans.Producto;
import com.lg.server.model.beans.SubFamilia;
import com.lg.server.model.dao.PMF;
import com.lg.server.model.logic.LogicProducto;
import com.lg.server.model.logic.LogicSubFamilia;
import com.lg.shared.BeanParametro;
import com.lg.shared.UnknownException;

public class MantProducto {
	private static final Logger LOG = Logger.getLogger(MantProducto.class
			.getName());

	public static Boolean insertarProducto(Producto bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("I")
				&& bean.getIdProducto() != null) {					
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();	
				Boolean resultado=insertarProducto(bean,pm);
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
	
	public static Boolean insertarProducto(Producto bean,PersistenceManager pm)throws UnknownException{
		Date fechaServer=new Date();
		LogicSubFamilia logicSubFamilia=new LogicSubFamilia(pm);
		SubFamilia beanSubFamilia=(SubFamilia)logicSubFamilia.getBean(bean.getCodeSubFamilia());
		bean.setVersion(fechaServer.getTime());
		bean.setBeanSubFamilia(beanSubFamilia);			
		beanSubFamilia.getListProducto().add(bean);		
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setTipoOperacion(bean.getOperacion());
		LogicProducto logic = new LogicProducto(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static Boolean actualizarProducto(Producto bean)
			throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("A")
				&& bean.getIdProducto() != null) {			
			PersistenceManager pm = null;
			Transaction tx = null;
			Boolean resultado=false;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Date fechaServer=new Date();
				BeanParametro parametro = new BeanParametro();				
				LogicProducto logic = new LogicProducto(pm);
				Producto beanAux=(Producto)logic.getBean(bean.getIdProducto());
				if(beanAux.getBeanSubFamilia().getIdSubFamilia().equalsIgnoreCase(bean.getBeanSubFamilia().getIdSubFamilia())){
					beanAux.setVersion(fechaServer.getTime());
					beanAux.setDescripcion(bean.getDescripcion());
					beanAux.setModelo(bean.getModelo());
					beanAux.setBeanMarca(bean.getBeanMarca());
					beanAux.setCodeMarca(bean.getCodeMarca());
					parametro.setBean(beanAux);
					parametro.setTipoOperacion(bean.getOperacion());
					resultado = logic.mantenimiento(parametro);
				}else{
					bean.setOperacion("E");
					resultado = eliminarProducto(bean,pm);
					if(resultado){
						bean.setIdProducto(bean.getBeanSubFamilia().getIdSubFamilia());
						bean.setOperacion("I");
						resultado = insertarProducto(bean,pm);
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

	public static Boolean eliminarProducto(Producto bean) throws UnknownException {
		if (bean.getOperacion().equalsIgnoreCase("E") 
				&& bean.getIdProducto() != null) {							
			PersistenceManager pm = null;
			Transaction tx = null;
			try {
				pm = PMF.getPMF().getPersistenceManager();
				tx = pm.currentTransaction();
				tx.begin();
				Boolean resultado=eliminarProducto(bean,pm);
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
	
	public static Boolean eliminarProducto(Producto bean,PersistenceManager pm)throws UnknownException{
		BeanParametro parametro = new BeanParametro();
		parametro.setBean(bean);
		parametro.setId(bean.getIdProducto());
		parametro.setTipoOperacion(bean.getOperacion());
		LogicProducto logic = new LogicProducto(pm);
		Boolean resultado = logic.mantenimiento(parametro);
		return resultado;
	}

	public static List<Producto> listarProducto() throws UnknownException {

		PersistenceManager pm = null;
		Transaction tx = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();				
			PMF.getPMF().getFetchGroup(Producto.class, "ProductoGroup").addMembers("beanSubFamilia","beanMarca");
			PMF.getPMF().getFetchGroup(SubFamilia.class, "SubFamiliaGroup").addMember("beanFamilia");
			pm.getFetchPlan().addGroup("SubFamiliaGroup");
			pm.getFetchPlan().addGroup("ProductoGroup");			
			pm.getFetchPlan().setMaxFetchDepth(2);
			tx = pm.currentTransaction();
			tx.begin();
			pm.setDetachAllOnCommit(true);
			LogicProducto logic = new LogicProducto(pm);
			List<Producto> resultado = (List<Producto>) pm.detachCopyAll(logic.getListarBean());
			Iterator<Producto> iterador=resultado.iterator();
			while(iterador.hasNext()){
				Producto bean=iterador.next();
				bean.setDescFamilia(bean.getBeanSubFamilia().getBeanFamilia().getDescripcion());				
			}			
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
