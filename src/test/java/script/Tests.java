package script;

import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Tests {


    static GroovyShell shell = new GroovyShell();
    @Test
    public void test1() throws ExecutionException, InterruptedException {


         Script thisScript = shell.parse("sleep 1000; return input1+' '+input2");

        Class<Script> clazz = (Class<Script>) thisScript.getClass();


        new Thread(() -> {
            try {
                System.out.println(runScript(clazz, "111", "aaa"));
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println(runScript(clazz, "222", "bbb"));
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
    }

    Object runScript(Class<Script> clazz, String param1, String param2) throws InstantiationException, IllegalAccessException {
        Script theScript = clazz.newInstance();
        theScript.setProperty("input1", param1);
        theScript.setProperty("input2", param2);
        Object result = theScript.run();
        return result;
    }

    Object runScript(Script theScript, String param1, String param2) {
        theScript.setProperty("input1", param1);
        theScript.setProperty("input2", param2);
        Object result = theScript.run();
        return result;
    }



}
