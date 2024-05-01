package com.redis.example.cachedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BasicController {
    
    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "cache/demo", method=RequestMethod.GET)
    public Map<String, Object> webServiceCall(@RequestParam String id) throws InterruptedException {
        Map<String, Object> cacheMap = redisService.getData(id);

        if(cacheMap.get(id) == null) {
            //make expensive ws call
            Thread.sleep(Duration.ofSeconds(10));
            redisService.saveData(id, "someData");
        }
       return redisService.getData(id);
    }   
}
