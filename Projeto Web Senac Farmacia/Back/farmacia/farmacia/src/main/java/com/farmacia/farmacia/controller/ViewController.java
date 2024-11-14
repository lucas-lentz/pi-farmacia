package com.farmacia.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/departamento")
    public String departamento() {
        return "departamento"; 
    }

    @GetMapping("/marca")
    public String marca() {
        return "marca"; 
    }

    @GetMapping("/categoria")
    public String categoria() {
        return "categoria"; 
    }

    @GetMapping("/produto")
    public String produto() {
        return "produto"; 
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
