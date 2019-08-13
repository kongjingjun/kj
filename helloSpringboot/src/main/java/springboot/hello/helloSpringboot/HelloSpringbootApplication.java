package springboot.hello.helloSpringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static springboot.hello.helloSpringboot.MyStartConfig.myStartConfig;

@SpringBootApplication
public class HelloSpringbootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //your logic
        myStartConfig();
    }
}
