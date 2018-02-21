package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 服务端写的cookie,将其写到客户端,写的就是登录时的jsessionid
 *
 * @author Shusheng Shi
 */
@Slf4j
public class CookieUtil {

    // 一级域名下的domain
    private final static String COOKIE_DOMAIN = ".shishusheng.com";
    private final static String COOKIE_NAME = "mmall_login_token";

    /**
     * 读取cookie
     * @param request
     * @return
     */
    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                log.info("read cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                //可以避免空指针异常
                if(StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    log.info("return cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 登录时写入cookie
     * @param response 需将cookie写入到此
     * @param token jsessionid
     */
    public static void writeLoginToken(HttpServletResponse response,String token){
        Cookie ck = new Cookie(COOKIE_NAME,token);
        ck.setDomain(COOKIE_DOMAIN);
        //代表设置在根目录 URLs that see the cookie
        ck.setPath("/");
        ck.setHttpOnly(true);
        //单位是秒
        //如果不设置maxage的话，cookie就不会写入硬盘，而是写在内存(即只在当前页面有效)
        //如果是-1，代表永久
        //有效期一年
        ck.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
        response.addCookie(ck);
    }

    /**
     * 注销登录时删除cookie
     * @param request
     * @param response
     */
    public static void delLoginToken(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    cookie.setDomain(COOKIE_DOMAIN);
                    cookie.setPath("/");
                    //设置成0，代表删除此cookie
                    cookie.setMaxAge(0);
                    log.info("del cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                    response.addCookie(cookie);
                    return;
                }
            }
        }
    }

}
