package springboot.hello.helloSpringboot.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import springboot.hello.helloSpringboot.dao.ServiceLogMapper;
import springboot.hello.helloSpringboot.entity.ServiceLog;
import springboot.hello.helloSpringboot.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static springboot.hello.helloSpringboot.util.BeanUtil.getBean;


public class InterceptorLog extends HandlerInterceptorAdapter {
    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
    private static final Logger logger = LoggerFactory.getLogger(InterceptorLog.class);

    //long beginTime;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        StringBuffer requestURL = httpServletRequest.getRequestURL();
        System.out.println("前置拦截器1 preHandle： 请求的uri为：" + requestURL.toString());
        // TODO Auto-generated method stub
        //if (logger.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();// 1、开始时间
            startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime), httpServletRequest.getRequestURI());
        //}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器1 postHandle： ");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("拦截器1 afterCompletion： ");
        //记录接口调用
        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();
        ServiceLog serviceLog = new ServiceLog();
        serviceLog.setAddress(httpServletRequest.getServletPath());
        serviceLog.setBeginTime(new Date(beginTime));
        serviceLog.setEndTime(new Date(endTime));
        serviceLog.setIp(StringUtil.getRemoteAddr(httpServletRequest));
        // 如果有异常，设置异常信息
        if (null != httpServletRequest.getAttribute("theException"))
            serviceLog.setError(httpServletRequest.getAttribute("theException").toString());//异常
        if (null != e)
            serviceLog.setError(getStackTraceAsString(e));//异常
        new Thread() {
            public void run() {
                ServiceLogMapper serviceLogMapper = getBean(ServiceLogMapper.class,httpServletRequest);
                serviceLogMapper.insert(serviceLog);
            }
        }.start();
    }

    private String getStackTraceAsString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}