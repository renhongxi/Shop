package com.vito16.shop.test;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class VitoTest {


    @Test
    public void t1(){
        RateLimiter limiter = RateLimiter.create(100);

        System.out.println(limiter.acquire(10));
        System.out.println(limiter.acquire(20));
        System.out.println(limiter.acquire(30));
    }
}
