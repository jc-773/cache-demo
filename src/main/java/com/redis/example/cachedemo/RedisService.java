package com.redis.example.cachedemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

   public Map<String, Object> getData(String key) {
        Map<String, Object> result = new HashMap<>();
        String value = redisTemplate.opsForValue().get(key); 
        result.put(key, value); 
        return result; 
    }
}
