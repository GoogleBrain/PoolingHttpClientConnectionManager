package com.king.rmi;

import java.rmi.*;

public interface IService extends Remote {

	String Service(String content) throws RemoteException;
}
