package com.farmacia.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login() {
        return "login"; // Pagina de Login customizada
    }

    @GetMapping("/index")
    public String openHome() {
        return "index"; // Pagina de Home customizada
    }


}
