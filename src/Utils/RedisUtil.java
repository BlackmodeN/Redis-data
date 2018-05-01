package Utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil   
{  
    private static JedisPool pool = null;  
      
    /** 
     * 获取jedis连接池 
     * */  
    public static JedisPool getPool()  
    {  
        if(pool == null)  
        {  
            //创建jedis连接池配置  
            JedisPoolConfig config = new JedisPoolConfig();  
            //最大连接数  
            config.setMaxTotal(100);  
            //最大空闲连接  
            config.setMaxIdle(5);  
            //创建redis连接池  
            pool = new JedisPool(config,"127.0.0.1",6379,90);  
        }  
        return pool;  
    }  
      
    /** 
     * 获取jedis连接 
     * */  
    public static Jedis getConn()  
    {  
        return getPool().getResource();  
    }  
}  