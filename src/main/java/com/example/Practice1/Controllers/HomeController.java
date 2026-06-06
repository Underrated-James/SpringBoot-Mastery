package com.example.Practice1.Controllers;

import com.example.Practice1.Entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/hello")
    public Message sayHello(){
        return new Message("Hello Worlds");
    }

}
