package com.lg.client.beanproxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocSubFamilia;
import com.lg.server.model.beans.SubFamilia;

@ProxyFor(value = SubFamilia.class, locator = LocSubFamilia.class)
public interface SubFamiliaProxy extends EntityProxy{
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public String getAbreviatura();
	public void setAbreviatura(String abreviatura);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public FamiliaProxy getBeanFamilia();
	public void setBeanFamilia(FamiliaProxy beanFamilia);
	public String getIdSubFamilia();
	public void setIdSubFamilia(String code);
	public String getCodeFamilia();
	public void setCodeFamilia(String codeFamilia);
	public String getCodeSubFamilia();
	public void setCodeSubFamilia(String codeSubFamilia);
	public Integer getEstadoActual();
	public void setEstadoActual(Integer estadoActual);
	public List<ProductoProxy> getListProducto();
	public void setListProducto(List<ProductoProxy> listProducto);
}
