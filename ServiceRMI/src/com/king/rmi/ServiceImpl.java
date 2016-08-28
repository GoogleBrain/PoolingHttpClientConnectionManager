package com.king.rmi;

import java.rmi.RemoteException;

public class ServiceImpl extends RemoteException implements IService {

	private static final long serialVersionUID = 1L;

	@Override
	public String Service(String content) throws RemoteException {
		return "Service>>>>" + content;
	}

}
