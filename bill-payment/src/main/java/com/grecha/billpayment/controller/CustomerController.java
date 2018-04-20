package com.grecha.billpayment.controller;

import com.grecha.billpayment.exception.ResourceNotFoundException;
import com.grecha.billpayment.model.Customer;
import com.grecha.billpayment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api"})
public class CustomerController {

    @Autowired
    CustomerRepository CustomerRepository;

    /// Get All
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return CustomerRepository.findAll();
    }


    // Create  new
    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return CustomerRepository.save(customer);
    }

    // Get a Single
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
        return CustomerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Biller", "id", customerId));
    }

    // Update
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
                                   @Valid @RequestBody Customer customerDetails) {
        Customer customer = CustomerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setAddress(customerDetails.getAddress());
        customer.setDateOfBirth(customerDetails.getDateOfBirth());

        Customer updatedCustomer = CustomerRepository.save(customer);
        return updatedCustomer;
    }

    // Delete
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable(value = "id") Long paymentId) {
        Customer customer = CustomerRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Biller", "id", paymentId));

        CustomerRepository.delete(customer);

        return ResponseEntity.ok().build();
    }
}
