package com.redis.example.cachedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
public class BasicController {
    
    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "cache/demo", method=RequestMethod.GET)
    public String webServiceCall(@RequestParam String id) throws InterruptedException {
       String data = redisService.getData(id);

        if(isNullOrEmpty(data)) {
            //mock expensive ws call that would return id
            Thread.sleep(Duration.ofSeconds(10));
            redisService.saveData(id, "someData");
        }
       return redisService.getData(id) + ": " + id;
    }   

    public static boolean isNullOrEmpty(String a) {
        return a == null || a.isEmpty() || a.isBlank();
    }
}
