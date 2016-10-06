package com.zs.common.web.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>User: jiarui
 * <p>Date: 15-12-16
 * <p>Version: 1.0
 */
@Controller
@ControllerAdvice
public class DefaultExceptionHandler {
    /**
     * 后续根据不同的需求定制即可
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("/wbem/msg/exception");
        return mv;
    }
    
    
    
    /**
	 * 权限errorAccess
	 * @author jixiaohang
	 *
	 */
	@RequestMapping("/wbem/msg/exception")
    public String userAccess(Model model) {
        return "/wbem/msg/exception";
    }
    /**
	 * error404
	 * @author jixiaohang
	 *
	 */
	@RequestMapping("/wbem/msg/error404")
    public String userError404(Model model) {
        return "/wbem/msg/error404";
    }
	/**
	 * error500
	 * @author jixiaohang
	 *
	 */
	@RequestMapping("/wbem/msg/error500")
    public String userError500(Model model) {
        return "/wbem/msg/error500";
    }
	
	
}
