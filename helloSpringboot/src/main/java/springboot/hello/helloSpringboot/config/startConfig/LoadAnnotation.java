package springboot.hello.helloSpringboot.config.startConfig;
import org.quartz.CronExpression;
import org.reflections.Reflections;
import springboot.hello.helloSpringboot.config.annotation.Time;
import springboot.hello.helloSpringboot.config.annotation.Times;
import springboot.hello.helloSpringboot.util.GetClasses;
import springboot.hello.helloSpringboot.util.YmlMapUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

import static org.reflections.Reflections.log;

public class LoadAnnotation {
    /**
     * 1为原始方法
     */
    public void loadAnnotation1() {

        // 得到包下所有的类/java
        List<Class<?>> classes = new GetClasses().getClasses((String) ((Map) YmlMapUtil.ymlMap.get("times")).get("path"));

        for (Class<?> clazz : classes) {
            //处理类上面Times注解
            loadTimes1(clazz);
        }
    }

    private void loadTimes1(Class<?> c) {
        if (c.isAnnotationPresent(Times.class)) {
            handleClassMethod(c);
        }
    }

    /**
     * 2用了Reflections的工具
     */

    public void  loadAnnotation2(){
        Reflections reflections = new Reflections((String) ((Map) YmlMapUtil.ymlMap.get("app")).get("path"));
        //处理Val注解
        loadVal(reflections);
        //处理Times注解
        loadTimes2(reflections);


    }
    private void loadTimes2(Reflections reflections) {
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(Times.class);
        for (Class c : classesList) {
            handleClassMethod(c);
        }
    }
    private void handleClassMethod(Class c){
        //获取类的所有方法
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            //处理Time注解的方法
            Time annotation = method.getAnnotation(Time.class);
            if (annotation != null) {
                //获取注解的value值
                String value = annotation.value();
                //解析cron表达式
                try {
                    CronExpression expression = new CronExpression(value);
                    Date curTime = new Date();
                    Date newDate = expression.getNextValidTimeAfter(curTime);
                    long timeInterval = (expression.getNextValidTimeAfter(newDate).getTime()-newDate.getTime());
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                method.invoke(c.newInstance());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Date(), timeInterval);
                } catch (ParseException e) {
                    log.error("fail to parse cron express", e);
                } catch (Exception e) {
                    log.error("fail to update rule nextTime", e);
                }

            }
        }
    }

    private void loadVal(Reflections reflections){
//        String[] beans = SpringContextHolder.getApplicationContext()
//                .getBeanDefinitionNames();
//        for (String beanName : beans) {
//            Class<?> beanType = SpringContextHolder.getApplicationContext()
//                    .getType(beanName);
//            System.out.println("BeanName:" + beanName);
//            System.out.println("Bean的类型：" + beanType);
//            System.out.println("Bean所在的包：" + beanType.getPackage());
//            System.out.println("Bean：" + SpringContextHolder.getApplicationContext().getBean(
//                    beanName));
//        }

//        Set<Field> fields = reflections.getFieldsAnnotatedWith(Val.class);
//        for(Field field : fields){
//            Val val = field.getAnnotation(Val.class);
//            String value = val.value();
//            Class<?> clazz = field.getDeclaringClass();
//            try {
//                field.set(clazz.newInstance(), YmlMapUtil.ymlMap.get(value));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
