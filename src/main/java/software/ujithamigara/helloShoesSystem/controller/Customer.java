package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.CustomerDTO;
import software.ujithamigara.helloShoesSystem.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class Customer {
    private final CustomerService customerService;
    @GetMapping("/health")
    public String healthTest(){
        return "Customer Health Test";
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customer) {
        return customerService.saveCustomer(customer);
    }
}
