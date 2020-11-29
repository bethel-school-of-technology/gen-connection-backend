package com.gencon.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    
    @GetMapping("/")
    public String index() {
        // the string index will be looked for in src/main/resources/templates
        return "index";
    }

}
