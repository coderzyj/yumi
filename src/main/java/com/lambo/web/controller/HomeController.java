package com.lambo.web.controller;

import com.lambo.base.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author : 张勇杰
 * @date : 2019/1/7 11:09
 * @Version : v1.0
 * @description
 **/
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","dapao");
        ModelAndView mv = new ModelAndView();
        return "index";
    }

    @GetMapping("/get")
    @ResponseBody
    public ApiResponse get(){
        return ApiResponse.ofMessage(200,"dapaoniubi");
    }

    @GetMapping("/404")
    public String notFoundPage(){
        return "404";
    }

    @GetMapping("/403")
    public String accessError(){
        return "403";
    }

    @GetMapping("/500")
    public String intenalError(){
        return "500";
    }

    @GetMapping("/logout")
    public String logoutPage(){
        return "logout";
    }
}
