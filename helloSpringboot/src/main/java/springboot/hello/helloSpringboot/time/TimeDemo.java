package springboot.hello.helloSpringboot.time;

import springboot.hello.helloSpringboot.config.annotation.Time;
import springboot.hello.helloSpringboot.config.annotation.Times;
import springboot.hello.helloSpringboot.config.annotation.Val;

import java.util.Date;

@Times
public class TimeDemo {
    @Val("a")
    public int a;

    public TimeDemo(int a) {
        this.a = a;
    }

    @Time("0 0/1 * * * ?")
    public void timeDemo(){
        System.out.println(new Date().getTime());
    }


    @Time("*/3 * * * * ?")
    public void timeDemo1(){
        System.out.println("============================="+a);
    }
}
