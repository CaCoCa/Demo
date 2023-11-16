package com.example.demo.javers;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import java.util.Date;
import java.util.List;

public class JaversTests {

    public static void main(String[] args) {
        Students students1 = Students.builder()
                .name("李绍俊")
                .addr("深圳市")
                .age(18)
                .books(Lists.newArrayList("Spring 实战", "DDD 领域驱动设计"))
                .birthday(new Date())
                .build();

        Students students2 = Students.builder()
                .name("李绍俊")
                .addr("成都市")
                .age(18)
                .books(Lists.newArrayList("Spring 实战", "DDD 领域驱动设计1", "重构"))
                .birthday(new Date())
                .build();

        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(students1, students2);

        System.out.println(diff.changesSummary());
        System.out.println(diff.getChanges().toString());
        System.out.println(diff.getChanges().devPrint());

        System.out.println(diff.toString());


    }



}


@Data
@Builder
class Students {
    private String name;
    private Integer age;
    private String addr;
    private List<String> books;
    private Date birthday;
}
