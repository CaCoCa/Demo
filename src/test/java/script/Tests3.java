package script;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Tests3 {

    @Test
    public void test1() throws Exception {

        GroovyShell groovyShell = new GroovyShell();

        String scriptText = "sleep time; return Java   + ' ' + Golang ";

        Map<String,Object> map = new HashMap<>();
        map.put("Java", "Spring");
        map.put("Golang", "Gin");

        Class<Script> parse = (Class<Script>) groovyShell.parse(scriptText).getClass();


        int[] times = new int[]{100, 200};

        System.out.println("***************** 非线程安全 *****************");
        for (int time : times) {
            map.put("time", time);
            if (time == 200) {
                // 重新给map赋值
                map.put("Java", "Spring2");
                map.put("Golang", "Gin2");
                new Thread(() -> {
                    try {
                        System.out.println(run(parse, map));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        System.out.println(run(parse, map));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
        // 根据时间判断，暴力等待所有线程执行完成
        TimeUnit.SECONDS.sleep(2);

        System.out.println("***************** 非线程安全 *****************");






    }


    private Object run(Script script, Map<String, Object> bindingMap) throws Exception{
        script = script.getClass().newInstance();
        Binding binding=new Binding();
        for (Map.Entry<String, Object> entry : bindingMap.entrySet()) {
            script.setProperty(entry.getKey(), entry.getValue());
            binding.setVariable(entry.getKey(), entry.getValue());
        }
        // script.setBinding(binding);
        Object ret=script.run();
        //script.setBinding(null);       //note 由于script可能会一直持有，这里把binding置null, 不再持有binding所引用的对象，便于垃圾回收
        return ret;
    }

    private Object run(Class<Script> clazz, Map<String, Object> bindingMap) throws Exception {
        Script script = clazz.newInstance();
        Binding binding=new Binding();
        for (Map.Entry<String, Object> entry : bindingMap.entrySet()) {
            script.setProperty(entry.getKey(), entry.getValue());
            binding.setVariable(entry.getKey(), entry.getValue());
        }
        // script.setBinding(binding);
        Object ret=script.run();
        //script.setBinding(null);       //note 由于script可能会一直持有，这里把binding置null, 不再持有binding所引用的对象，便于垃圾回收
        return ret;
    }
}
