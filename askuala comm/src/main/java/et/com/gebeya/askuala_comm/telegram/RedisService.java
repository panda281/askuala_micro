package et.com.gebeya.askuala_comm.telegram;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.exceptions.JedisException;

@Component

public class RedisService {
    @Qualifier("user")
    private final RedisTemplate<String, Long> userRedisTemplate;
    @Qualifier("userstate")
    private final RedisTemplate<Long, UserState> userStateRedisTemplate;

    @Qualifier("cache")
    private final RedisTemplate<Long,Long> cacheRedisTemplate;

    @Qualifier("sender")
    private final RedisTemplate<Long,String> senderRedisTemplate;

    public RedisService(RedisTemplate<String, Long> userRedisTemplate, RedisTemplate<Long, UserState> userStateRedisTemplate, RedisTemplate<Long, Long> cacheRedisTemplate, RedisTemplate<Long, String> senderRedisTemplate) {
        this.userRedisTemplate = userRedisTemplate;
        this.userStateRedisTemplate = userStateRedisTemplate;
        this.cacheRedisTemplate = cacheRedisTemplate;
        this.senderRedisTemplate = senderRedisTemplate;
    }

    public void setUserState(Long key, UserState value) {
        try {
            userStateRedisTemplate.opsForValue().set(key, value);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public UserState getUserState(Long key) {
        try {
            return userStateRedisTemplate.opsForValue().get(key);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public void setCache(Long key, Long value) {
        try {
            cacheRedisTemplate.opsForValue().set(key, value);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Long getCache(Long key) {
        try {
            return cacheRedisTemplate.opsForValue().get(key);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    public void setUser(String key, Long value){
        try{
            userRedisTemplate.opsForValue().set(key, value);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Long getUser(String key){
        try {
            return userRedisTemplate.opsForValue().get(key);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void setSender(Long key, String value){
        try{
            senderRedisTemplate.opsForValue().set(key, value);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String getSender(Long key){
        try {
            return senderRedisTemplate.opsForValue().get(key);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }



}
