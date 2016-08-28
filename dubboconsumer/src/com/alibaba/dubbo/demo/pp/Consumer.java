package com.alibaba.dubbo.demo.pp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unj.dubbotest.provider.DemoService;

public class Consumer {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService");
		String hello = demoService.sayHello("tom");
		System.out.println(hello);

		System.in.read();
	}

}