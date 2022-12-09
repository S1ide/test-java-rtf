package com.ulearn.exercise.repository;

import com.ulearn.exercise.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}