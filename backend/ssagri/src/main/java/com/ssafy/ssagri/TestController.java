package com.ssafy.ssagri;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {
    @GetMapping
    public String test() {
        System.out.println("TEST");
        return "TEST";
    }
}
