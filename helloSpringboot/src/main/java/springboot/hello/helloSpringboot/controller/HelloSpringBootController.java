package springboot.hello.helloSpringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.hello.helloSpringboot.config.annotation.UserLoginToken;
import springboot.hello.helloSpringboot.entity.User;
import springboot.hello.helloSpringboot.service.HelloSpringBootService;


@RestController
@RequestMapping("/demo")
public class HelloSpringBootController {
    @Autowired
    private HelloSpringBootService helloSpringBootService;
    @RequestMapping(value = "/helloSpringBoot", method = RequestMethod.POST)
    @UserLoginToken
    public User HelloSpring (@RequestBody User user){
        System.out.println("hello spring boot");
        return helloSpringBootService.helloSpring(user);
    }
}
