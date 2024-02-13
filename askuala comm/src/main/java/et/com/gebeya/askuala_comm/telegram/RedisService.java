package et.com.gebeya.askuala_comm.telegram;

import et.com.gebeya.askuala_comm.dto.UserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.exceptions.JedisException;

@Component

public class RedisService {
    @Qualifier("user")
    private final RedisTemplate<String, Long> userRedisTemplate;

    @Qualifier("cache")
    private final RedisTemplate<Long, UserDto> cacheRedisTemplate;


    public RedisService(RedisTemplate<String, Long> userRedisTemplate,  RedisTemplate<Long, UserDto> cacheRedisTemplate) {
        this.userRedisTemplate = userRedisTemplate;
        this.cacheRedisTemplate = cacheRedisTemplate;
    }



    public void setCache(Long key, UserDto value) {
        try {
            cacheRedisTemplate.opsForValue().set(key, value);
        } catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public UserDto getCache(Long key) {
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


    public void setState(Long key,UserState state){
        try{
            UserDto userDto = cacheRedisTemplate.opsForValue().get(key);
            assert userDto != null;
            userDto.setState(state);
            cacheRedisTemplate.opsForValue().set(key,userDto);
        }
        catch (JedisException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


}
