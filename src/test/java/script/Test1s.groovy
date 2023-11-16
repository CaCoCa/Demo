package script

import org.junit.jupiter.api.Test

import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

public class Test1s {

    static GroovyShell shell = new GroovyShell();

    @Test
    public void test1() throws ExecutionException, InterruptedException {


        Class<Script> scriptClass = shell.parse("sleep 1000; return input1+' '+input2").getClass();

        Script scriptClass2 = shell.parse("sleep 1000; return input1+' '+input2");

        def clazz = scriptClass2.getClass()


        /*[
                ["111","aaa"],
                ["222","bbb"]
        ].collect{x->
            Thread.start{
                println "start $x"
                println runScript(scriptClass2, x[0], x[1])
            }
        }*.join()*/

        new Thread(() -> {
            println runScript(clazz, "111", "aaa")
        }).start()
        new Thread(() -> {
            println runScript(clazz, "222", "bbb")
        }).start()

        TimeUnit.SECONDS.sleep(3)


    }

    Object runScript(Class<Script> clazz, String param1, String param2) {
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
