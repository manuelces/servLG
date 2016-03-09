package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.locator.LocProducto;
import com.lg.server.model.beans.Producto;

@ProxyFor(value = Producto.class, locator = LocProducto.class)
public interface ProductoProxy extends EntityProxy{
	public String getIdProducto();
	public void setIdProducto(String idSubFamilia);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public String getModelo();
	public void setModelo(String modelo);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public String getCodeProducto();
	public void setCodeProducto(String codeProducto);
	public SubFamiliaProxy getBeanSubFamilia();
	public void setBeanSubFamilia(SubFamiliaProxy beanSubFamilia);
	public String getCodeSubFamilia();
	public void setCodeSubFamilia(String codeSubFamilia);
	public MarcaProxy getBeanMarca();
	public void setBeanMarca(MarcaProxy beanMarca);
	public String getCodeMarca();
	public void setCodeMarca(String codeMarca);
	public Integer getEstadoActual();
	public void setEstadoActual(Integer estadoActual);
	public String getCodeFamilia();
	public void setCodeFamilia(String codeFamilia);
	public String getDescFamilia();
	public void setDescFamilia(String descFamilia);
}
