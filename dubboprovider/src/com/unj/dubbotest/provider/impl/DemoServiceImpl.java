package com.unj.dubbotest.provider.impl;

import com.unj.dubbotest.provider.DemoService;

public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		return "Hello " + name;
	}
}
