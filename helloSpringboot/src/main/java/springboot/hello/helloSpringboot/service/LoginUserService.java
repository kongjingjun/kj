package springboot.hello.helloSpringboot.service;

import springboot.hello.helloSpringboot.entity.LoginUser;

public interface LoginUserService {
    LoginUser findUserById(String Id);
}
