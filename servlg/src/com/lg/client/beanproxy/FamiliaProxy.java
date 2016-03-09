package com.lg.client.beanproxy;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocFamilia;
import com.lg.server.model.beans.Familia;

@ProxyFor(value = Familia.class, locator = LocFamilia.class)
public interface FamiliaProxy extends EntityProxy{
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public String getAbreviatura();
	public void setAbreviatura(String abreviatura);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public List<SubFamiliaProxy> getListSubFamilia();
	public void setListSubFamilia(List<SubFamiliaProxy> listSubFamilia);
	public String getIdFamilia();
	public void setIdFamilia(String idFamilia);
	public String getCodeFamilia();
	public void setCodeFamilia(String codeFamilia);
	public Integer getEstadoActual();
	public void setEstadoActual(Integer estadoActual);
}
