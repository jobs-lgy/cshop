package com.javachen.cshop.common.web;

import com.javachen.cshop.common.auth.AuthUser;
import com.javachen.cshop.common.auth.JwtHelper;
import com.javachen.cshop.common.exception.ErrorCode;
import com.javachen.cshop.common.utils.CookieUtils;
import com.javachen.cshop.common.utils.json.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 定义一个线程域，存放登录用户
     */
    private static final ThreadLocal<AuthUser> t1 = new ThreadLocal<>();
    @Autowired
    private JwtHelper jwtHelper;

    public static AuthUser getLoginUser() {
        return t1.get();
    }

    /**
     * * 在业务处理器处理请求之前被调用
     * * 如果返回false
     * *      则从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * * 如果返回true
     * *      执行下一个拦截器，直到所有拦截器都执行完毕
     * *      再执行被拦截的Controller
     * *      然后进入拦截器链
     * *      从最后一个拦截器往回执行所有的postHandle()
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.查询token
        String token = CookieUtils.getCookieValue(request, jwtHelper.getCookieName());
        if (StringUtils.isEmpty(token)) {
            //2.未登录，返回401
            log.warn("没有授权访问");

            unauthorized(response);
            return false;
        }
        //3.有token，查询用户信息
        try {
            //4.解析成功，说明已经登录
            AuthUser userInfo = jwtHelper.getAuthUserFromToken(token);
            //5.放入线程域
            t1.set(userInfo);
            return true;
        } catch (Exception e) {
            //6.抛出异常，证明未登录，返回401
            log.error("登陆异常", e);
            unauthorized(response);
            return false;
        }
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ObjectMapperUtils.obj2json(ErrorCode.UNAUTHORIZED));
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        t1.remove();
    }
}
