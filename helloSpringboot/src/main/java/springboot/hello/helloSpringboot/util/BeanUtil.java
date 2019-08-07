package springboot.hello.helloSpringboot.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {

    /**
     * 获取Bean
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz, HttpServletRequest request){
        BeanFactory factory= WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());

        return factory.getBean(clazz);


    }
}
