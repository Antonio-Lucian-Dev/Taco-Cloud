package com.asusoftware.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 1 Variante
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
