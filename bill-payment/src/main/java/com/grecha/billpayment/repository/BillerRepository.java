package com.grecha.billpayment.repository;

import com.grecha.billpayment.model.Biller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long> {
}
