package springboot.hello.helloSpringboot.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new InterceptorLog()).addPathPatterns("/**");
    }
}

