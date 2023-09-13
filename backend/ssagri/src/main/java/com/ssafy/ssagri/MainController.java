package com.ssafy.ssagri;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping("/hello")
    public String main(){
        return "Hello";
    }
}
