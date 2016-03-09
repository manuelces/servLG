package com.lg.client.requestfactory;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.Service;
import com.lg.client.beanproxy.UbigeoProxy;
import com.lg.server.model.process.MantUbigeo;

@Service(value = MantUbigeo.class)
public interface CntxMantUbigeo {

	Request<Boolean> insertarUbigeo(UbigeoProxy bean);

	Request<List<UbigeoProxy>> listarUbigeo();

}
