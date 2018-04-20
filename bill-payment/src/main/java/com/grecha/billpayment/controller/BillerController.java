package com.grecha.billpayment.controller;

import com.grecha.billpayment.exception.ResourceNotFoundException;
import com.grecha.billpayment.model.Biller;
import com.grecha.billpayment.repository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BillerController {

    @Autowired
    BillerRepository billerRepository;

    /// Get All
    @GetMapping("/billers")
    public List<Biller> getAllBillers() {
        return billerRepository.findAll();
    }


    // Create  new
    @PostMapping("/billers")
    public Biller createBiller(@Valid @RequestBody Biller biller) {
        return billerRepository.save(biller);
    }

    // Get a Single
    @GetMapping("/billers/{id}")
    public Biller getBillerById(@PathVariable(value = "id") Long billerId) {
        return billerRepository.findById(billerId)
                .orElseThrow(() -> new ResourceNotFoundException("Biller", "id", billerId));
    }

    // Update
    @PutMapping("/billers/{id}")
    public Biller updateBiller(@PathVariable(value = "id") Long billerId,
                                 @Valid @RequestBody Biller customerDetails) {
        Biller biller = billerRepository.findById(billerId)
                .orElseThrow(() -> new ResourceNotFoundException("Biller", "id", billerId));

        biller.setName(customerDetails.getName());

        Biller updatedBiller = billerRepository.save(biller);
        return updatedBiller;
    }

    // Delete
    @DeleteMapping("/billers/{id}")
    public ResponseEntity<?> deleteBiller(@PathVariable(value = "id") Long billerId) {
        Biller biller = billerRepository.findById(billerId)
                .orElseThrow(() -> new ResourceNotFoundException("Biller", "id", billerId));

        billerRepository.delete(biller);

        return ResponseEntity.ok().build();
    }
}
