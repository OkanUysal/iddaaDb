package uysal.iddaa.iddaaDb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String retrieveCoursesForStudent() {
        return "Hello World";
    }
}