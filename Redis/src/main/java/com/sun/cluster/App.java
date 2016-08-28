package com.sun.cluster;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class App {
	public static void main(String[] args) throws Exception {

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(2);

		HostAndPort h0 = new HostAndPort("127.0.0.1", 7000);
		HostAndPort h1 = new HostAndPort("127.0.0.1", 7001);
		HostAndPort h2 = new HostAndPort("127.0.0.1", 7002);
		HostAndPort h3 = new HostAndPort("127.0.0.1", 7003);
		HostAndPort h4 = new HostAndPort("127.0.0.1", 7004);
		HostAndPort h5 = new HostAndPort("127.0.0.1", 7005);
		HostAndPort h6 = new HostAndPort("127.0.0.1", 7006);

		Set<HostAndPort> hps = new HashSet<HostAndPort>();
		hps.add(h0);
		hps.add(h1);
		hps.add(h2);
		hps.add(h3);
		hps.add(h4);
		hps.add(h5);
		hps.add(h6);

		JedisCluster jedisCluster = new JedisCluster(hps, 5000, 10, config);

//		for (int i = 0; i < 100; i++) {
//			jedisCluster.set("sn" + i, "n" + i);
//		}

		for (int i = 0; i < 100; i++) {
			System.out.println(jedisCluster.get("sn" + i));
		}
		jedisCluster.close();
	}
}