package com.example.demo.web;

import com.example.demo.cache.CacheKey;
import com.example.demo.cache.MicaRedisProperties;
import com.example.demo.cache.RedisCache;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(description = "服务类：测试",name = "test")
public class TestController {

    @Autowired
    private Environment environment;

    @Autowired
    private MicaRedisProperties micaRedisProperties;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/test")
    @Operation(summary = "Test",tags = "test")
    public String test() {

        System.out.println(environment.getProperty("server.port"));

        return LocalDateTime.now().toString();
    }

    @GetMapping("/infweb/services/test")
    @Operation(summary = "Test1",tags = "test")
    public String test1() {

        return LocalDateTime.now().toString();
    }

    @PostMapping("/infweb/services/test1")
    @Operation(summary = "Test2",tags = "test2")
    public String test2(String str) {



        return LocalDateTime.now().toString();
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @Operation(summary = "测试文件上传", tags = "test2")
    public String upload(@RequestParam("files") List<MultipartFile> files,
                         @RequestParam(value = "operate", required = false) String operate) {

        return LocalDateTime.now().toString();
    }

    @GetMapping("/test2")
    public void test2() {
        String a = "123123";
        System.out.println(a);
        redisCache.set(new CacheKey("key"), "123564");
        System.out.println(micaRedisProperties.getKeyPrefix());
    }
}
