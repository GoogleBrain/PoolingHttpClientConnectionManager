package com.king.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] args) throws Exception {

		IService service02 = new ServiceImpl();
		LocateRegistry.createRegistry(8888);
		Naming.bind("rmi://localhost:8888/ser02", service02);
		System.out.print("提供了一个服务。。。。。");
		while (true) {

		}
	}
}
