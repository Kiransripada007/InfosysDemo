package com.yamuna.demo.repository;

import com.yamuna.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {


    Customer findByEmailAddressAndPassword(String emailAddress, String password);

    Customer findByEmailAddress(String emailAddress);
}
