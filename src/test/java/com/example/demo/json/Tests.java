package com.example.demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class Tests {



    @Test
    public void json() throws JsonProcessingException {

        String json = """
                {
                  "id" : 9,
                  "username" : "Panda",
                  "password" : "abcd",
                  "age" : 20,
                  "gender" : "男",
                  "email" : "abcd@qq.com"
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);
 ;


        // 将JSON字符串解析为JsonNode
        Object jsonNode = objectMapper.readValue(json,Object.class);


        System.out.println(objectMapper.writeValueAsString(jsonNode));





        /*try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

    }
}
