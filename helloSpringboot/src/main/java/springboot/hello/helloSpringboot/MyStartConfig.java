package springboot.hello.helloSpringboot;

import springboot.hello.helloSpringboot.config.startConfig.LoadAnnotation;
import springboot.hello.helloSpringboot.config.startConfig.LoadYmlParam;

public class MyStartConfig {

    public static void myStartConfig(){
        new LoadYmlParam().getProperties();
        //new LoadAnnotation().loadAnnotation1();//加载自定义注解（原始方法）
        new LoadAnnotation().loadAnnotation2();//加载自定义注解（通过工具类的方式）
    }
}
