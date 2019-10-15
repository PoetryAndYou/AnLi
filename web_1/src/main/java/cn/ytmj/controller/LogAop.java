package cn.ytmj.controller;

import cn.ytmj.domain.SysLog;
import cn.ytmj.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author rui
 * @create 2019-10-14 19:48
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;//开启时间
    private Class clazz;//放任的类
    private Method method;//访问的方法
    private String url;
    private String username;
    private Long time;
    private String ip;


    //前置通知 获取开启时间 执行的类是哪一个 执行的是哪个方法
    @Before("execution(* cn.ytmj.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass(); //具体要访问的类
        String methodName = jp.getSignature().getName(); //获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            try {
                clazz.getMethod(methodName, classArgs);
            } catch (Exception e) {
                System.err.println("aaaaaaaaaaaaaaaaaa");
                e.printStackTrace();
            }

        }
    }

    //后置通知
    @After("execution(* cn.ytmj.controller.*.*(..))")
    public void doAfter() throws Exception {
        Date date = new Date();
        //访问时长
        time = date.getTime() - visitTime.getTime();

        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //获取注解
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] classValue = clazzAnnotation.value();
                //获取方法上的注解
                RequestMapping methodAnnotation = (RequestMapping) method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                    //获取访问的ip地址
                    ip = request.getRemoteAddr();
                    //获取当前登录的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    username = user.getUsername();
                    //封装对象
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setExecutionTime(time);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());

                    //调用Service
                    sysLogService.save(sysLog);
                }
            }
        }

    }

}
