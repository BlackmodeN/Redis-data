package Dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import Utils.RedisUtil;
import redis.clients.jedis.Jedis;

public class WriteReadRedis {
	private static volatile WriteReadRedis instance = null;
	private final Map<String, String> map = new ConcurrentHashMap<String, String>();

	private WriteReadRedis() {

	};

	public static WriteReadRedis getInstantce() {
		if (instance == null) {
			synchronized (WriteReadRedis.class) {
				if (instance == null) {
					instance = new WriteReadRedis();
				}
			}
		}
		return instance;
	}

	@Test
	public void writeHumidityInformation(String h1, String h2, String h3, String h4, String h5, String h6) {
		map.put("设备1：", h1);
		map.put("设备2：", h2);
		map.put("设备3：", h3);
		map.put("设备4：", h4);
		map.put("设备5：", h5);
		map.put("设备6：", h6);
		Jedis conn = RedisUtil.getConn();
		conn.hmset("HumidityInformation", map);
		List<String> rsmap = conn.hmget("HumidityInformation");
		conn.close();
		for (String h : rsmap)
			System.out.println(h); // test
	}
	public List getAllHumidityInformation(){
		Jedis conn = RedisUtil.getConn();
		List<String> data = conn.hmget("HumidityInformation", "设备1", "设备2","设备3","设备4","设备5","设备6");
		conn.close();
		return data;
	}
	

}
