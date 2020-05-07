package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "/Employee")
public class CustomSpringController {
	
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}