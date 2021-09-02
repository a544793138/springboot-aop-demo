package com.tjwoods.controller;

import com.tjwoods.annotation.Action;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/test/aop")
    @Action(description = "调用了 /test/aop 接口")
    public String testAop() {
        return "service test: " + Thread.currentThread().getName();
    }

    @GetMapping("/test/no-aop")
    public String testNoAop() {
        return "service testNoAop: " + Thread.currentThread().getName();
    }

    @GetMapping("/test/aop/throws")
    @Action(description = "调用了 /test/aop/throw 接口，这个接口会抛异常")
    public String testAopThrows() {
        throw new IllegalStateException("service testAopThrows: " + Thread.currentThread().getName());
    }
}
