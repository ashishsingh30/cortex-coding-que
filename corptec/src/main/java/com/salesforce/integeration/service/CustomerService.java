package com.salesforce.integeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesforce.integeration.model.Customer;
import com.salesforce.integeration.repo.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private SalesforceService salesforceService;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(String id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
    
    public void synchronizeWithSalesforce() {
        // Fetching customers from Salesforce
        List<Customer> salesforceCustomers = salesforceService.getUsersFromSalesforce();

        
        for (Customer sfCustomer : salesforceCustomers) {
            Optional<Customer> dbCustomer = customerRepository.findById(sfCustomer.getId());
            if (dbCustomer.isPresent()) {
                
                updateCustomer(sfCustomer.getId(), sfCustomer);
            } else {
               
                createCustomer(sfCustomer);
            }
            
        }
    }
}
