package com.example.demo.redisson;


import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
public class Tests {


    @Autowired
    private RedissonClient redissonClient;



   @Test
    public void test1() {
       RAtomicLong atomicLong = redissonClient.getAtomicLong("kj");


       long andGet = atomicLong.incrementAndGet();
       atomicLong.expireIfNotSet(Duration.ofHours(5));
       System.out.println(andGet);

    }

    @Test
    public void test2() {





    }



}
