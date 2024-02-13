package et.com.gebeya.askuala_comm.config;

import et.com.gebeya.askuala_comm.dto.UserDto;
import et.com.gebeya.askuala_comm.telegram.UserState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean(name = "user")
    public RedisTemplate<String, Long> userRedisTemplate() {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);

        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Long.class));
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer<>(Long.class));

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "userstate")
    public RedisTemplate<Long, UserState> userStateRedisTemplate() {
        RedisTemplate<Long, UserState> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Long.class));
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(UserState.class));

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "cache")
    public RedisTemplate<Long, UserDto> cacheRedisTemplate() {
        RedisTemplate<Long, UserDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        // Set key serializer to convert Long to String
        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Long.class));
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Long.class));

        // Set value serializer to JSON serializer
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserDto.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(UserDto.class));

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }





}
