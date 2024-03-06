package com.yamuna.demo.controller;

import com.yamuna.demo.entity.Customer;
import com.yamuna.demo.exception.CustomerNotFoundException;
import com.yamuna.demo.exception.InvalidCustomerIdException;
import com.yamuna.demo.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CustomerController {


    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping("/register/customer")
    public ResponseEntity<?> createStudent(@RequestBody Customer customer) throws CustomerNotFoundException {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).body(createdCustomer);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<?> updateStudent(@PathVariable long customerId, @RequestBody Customer customerX) throws CustomerNotFoundException {
        Customer customer = customerService.updateCustomer(customerId, customerX);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() throws CustomerNotFoundException {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable int customerId) throws CustomerNotFoundException, InvalidCustomerIdException {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable int customerId) throws CustomerNotFoundException {
        String customer=customerService.deleteCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
