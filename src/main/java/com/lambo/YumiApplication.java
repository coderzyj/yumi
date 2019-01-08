package com.lambo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YumiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YumiApplication.class, args);
    }

    /*@GetMapping(name = "/hello")
    public String hello(){
        return "hello,zhang";
    }*/
}

