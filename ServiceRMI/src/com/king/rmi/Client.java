package com.king.rmi;

import java.rmi.Naming;

public class Client {
	public static void main(String[] args) throws Exception {
		IService service = (IService) Naming
				.lookup("rmi://localhost:8888/ser02");
		System.out.print(service.Service("Hello World"));
	}
}
