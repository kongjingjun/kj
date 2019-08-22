package springboot.hello.helloSpringboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import springboot.hello.helloSpringboot.entity.LoginUser;

public class GetToken {
    public static String getToken(LoginUser user) {
        String token="";
        token= JWT.create().withAudience("zhangsan")
                .sign(Algorithm.HMAC256("123456"));
        System.out.println(token);
        return token;
    }

    public static void main(String[] args) {
        getToken(null);
    }
}