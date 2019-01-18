package com.lambo.base;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author : 张勇杰
 * @date : 2019/1/7 16:43
 * @Version : v1.0
 * @description
 **/
@Controller
public class AppErrorController implements ErrorController {
    private static final String ERROR_PATH="/error";

    private ErrorAttributes errorAttributes;
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    public AppErrorController(ErrorAttributes errorAttributes){
        this.errorAttributes = errorAttributes;
    }

    /**
     * Web页面错误处理
     */
    @RequestMapping(value = ERROR_PATH,produces = "text/html")
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response){
        int status = response.getStatus();
        switch (status){
            case 403:
                return "403";
            case 404:
                return "404";
            case 500:
                return "500";
        }
        return "index";
    }

    /**
     * 除web页面外的错误处理
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ApiResponse errorApiHandler(HttpServletRequest request){
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String,Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes,false);
        int status = getStatus(request);

        return ApiResponse.ofMessage(status,String.valueOf(attr.getOrDefault("message","error")));
    }

    private int getStatus(HttpServletRequest request){
        Integer status = (Integer) request.getAttribute("java.servlet.error.status_code");
        if(status != null){
            return status;
        }
        return 500;
    }
}
