package org.yunzhong.eureka.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonRestController {

    @RequestMapping("/")
    public String rest() {
        return "hello world client2!";
    }
}
