package software.ujithamigara.helloShoesSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class Customer {

    @GetMapping("/health")
    public String healthTest(){
        return "Customer Health Test";
    }

}
