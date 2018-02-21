package com.mmall.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理全局异常
 *
 * @author Shusheng Shi
 */
@Slf4j
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.error( "{} Exception", httpServletRequest.getRequestURI(), e );

        //不需要跳转到一个view界面,所以将其转化为json view,信息与服务端响应信息保持一致
        ModelAndView modelAndView=new ModelAndView( new MappingJacksonJsonView() );
        modelAndView.addObject( "status", ResponseCode.ERROR.getCode() );
        modelAndView.addObject( "msg", "接口异常,详情请查看服务端日志的异常信息" );
        modelAndView.addObject( "data", e.toString() );
        return modelAndView;
    }

}
