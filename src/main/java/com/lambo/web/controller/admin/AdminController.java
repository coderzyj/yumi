package com.lambo.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 *
 * @author : 张勇杰
 * @date : 2019/2/12 16:03
 * @Version : v1.0
 * @description
 **/
@Controller
public class AdminController {
    @GetMapping("/admin/center")
    public String adminCenterPage(){
        LinkedHashMap map = new LinkedHashMap();

        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "admin/welcome";
    }

    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }
}
