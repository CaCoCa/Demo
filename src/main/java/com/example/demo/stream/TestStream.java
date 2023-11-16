package com.example.demo.stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {

    public static void main(String[] args) {
        List<String> strings = Lists.newArrayList("Hello World", "你好 李绍俊", "1231 222");
        List<String> stringList = strings.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());
        System.out.println(stringList);
    }
}
