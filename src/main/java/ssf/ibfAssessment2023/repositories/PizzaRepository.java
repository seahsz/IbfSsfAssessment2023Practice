package ssf.ibfAssessment2023.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PizzaRepository {

    @Autowired
    @Qualifier("redis-string")
    private RedisTemplate<String, String> redisTemplate;

    // set order_id json_string
    public void saveOrderAsJsonString(String orderId, String jsonString) {
        redisTemplate.opsForValue().set(orderId, jsonString);
    }

    // get order_id
    public Optional<String> getJsonStringById(String orderId) {
        if (!redisTemplate.hasKey(orderId))
            return Optional.empty();

        return Optional.of(redisTemplate.opsForValue().get(orderId));
    }
    
}
