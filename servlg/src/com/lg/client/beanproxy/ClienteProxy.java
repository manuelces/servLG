package com.lg.client.beanproxy;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.lg.server.model.beans.Cliente;
import com.lg.server.locator.LocCliente;

@ProxyFor(value = Cliente.class, locator = LocCliente.class)
public interface ClienteProxy extends EntityProxy{
	public String getCodeCliente();
	public void setCodeCliente(String codeCliente);
	public String getIdCliente();
	public void setIdCliente(String idCliente);
	public TipoDocIdenProxy getBeanTipoDocIden();
	public void setBeanTipoDocIden(TipoDocIdenProxy beanTipoDocIden);
	public String getNumDocumento();
	public void setNumDocumento(String numDocumento);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
	public DistritoProxy getBeanDistrito();
	public void setBeanDistrito(DistritoProxy beanDistrito);
	public String getDireccion();
	public void setDireccion(String direccion);
	public String getTipoPersona();
	public void setTipoPersona(String tipoPersona);
	public String getCelular();
	public void setCelular(String celular);
	public String getTelefono();
	public void setTelefono(String telefono);
	public Long getVersion();
	public void setVersion(Long version);
	public String getOperacion();
	public void setOperacion(String operacion);
	public String getCodeProvincia();
	public void setCodeProvincia(String codeProvincia);
	public String getDescProvincia();
	public void setDescProvincia(String descProvincia);
	public String getCodeDepartamento();
	public void setCodeDepartamento(String codeDepartamento);
	public String getDescDepartamento();
	public void setDescDepartamento(String descDepartamento);
	public String getCodePais();
	public void setCodePais(String codePais);
	public String getDescPais();
	public void setDescPais(String descPais);
}
