package springboot.hello.helloSpringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloSpringbootApplicationTests {

    /**
     * java反射测试
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    @Test
    public void contextLoads() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //Class<?> clazz = HelloSpringbootApplicationTests.class;
        Class<?> clazz = Class.forName("springboot.hello.helloSpringboot.HelloSpringbootApplicationTests");

        Object object = clazz.newInstance();

        Method[] methods = clazz.getMethods();
        // 调用Person类中的run方法
        Method method = clazz.getDeclaredMethod("run");
        // Method method = clazz.getMethod("run");
        method.invoke(object);
        // Java 反射机制 - 调用某个类的方法1.
        // 调用Person的Speak方法
        method = clazz.getDeclaredMethod("Speak", int.class, String.class);
        method.invoke(object, 22, "小明");
        // Java 反射机制 - 调用某个类的方法2.
        // age -> 22. name -> 小明
    }

    public void run() {
        System.out.println("调用Person类的run方法");
    }

    public void Speak(int age, String name) {
        System.out.println("调用Person类的Speak方法");
        System.out.println("age -> " + age + ". name -> " + name);
    }

}
