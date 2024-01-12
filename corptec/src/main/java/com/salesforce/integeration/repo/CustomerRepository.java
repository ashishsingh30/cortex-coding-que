package com.salesforce.integeration.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesforce.integeration.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}

