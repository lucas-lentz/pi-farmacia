package com.farmacia.farmacia.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("controllerStatus")
public class ControllerStatusApplication {

    @RequestMapping("/ok") 
    public String statusOk() {
        return "OK";
    }
}
