package com.yamuna.demo.service;

import com.yamuna.demo.entity.Customer;
import com.yamuna.demo.exception.CustomerNotFoundException;
import com.yamuna.demo.exception.InvalidCustomerIdException;
import com.yamuna.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl {


        @Autowired
        private CustomerRepo customerRepository;



        public Customer createCustomer(Customer customer) {
            return customerRepository.save(customer);
        }


        public Customer updateCustomer(Long customerId, Customer customer) throws CustomerNotFoundException {
            Customer existingCustomer =  customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Invalid UserId"));
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setEmailAddress(customer.getEmailAddress());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setSubmittingTransferAgent(customer.getSubmittingTransferAgent());
            existingCustomer.setCusip(customer.getCusip());
            existingCustomer.setInputForm(customer.getInputForm());
            existingCustomer.setCusipType(customer.getCusipType());
            existingCustomer.setIssuerName(customer.getIssuerName());
            existingCustomer.setIssueDescription(customer.getIssueDescription());
            existingCustomer.setEffectiveDate(customer.getEffectiveDate());
            existingCustomer.setTransferAgentName(customer.getTransferAgentName());
            existingCustomer.setTransferAgentNumber(customer.getTransferAgentNumber());
            existingCustomer.setTransferAgentAddress(customer.getTransferAgentAddress());
            existingCustomer.setFins(customer.getFins());
            return customerRepository.save(existingCustomer);
        }

        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }


        public Customer getCustomerById(int CustomerId) throws InvalidCustomerIdException {
            return customerRepository.findById((long) CustomerId).orElseThrow(() -> new InvalidCustomerIdException("Invalid  CustomerId"));
        }


        public String deleteCustomerById(int customerId) throws CustomerNotFoundException {
            try {
                customerRepository.deleteById((long) customerId);
                return "User successfully deleted";
            }
            catch(Exception ex){
                throw new CustomerNotFoundException("Invalid userId");
            }
        }

        public Customer login(String emailAddress, String Password) {
            return customerRepository.findByEmailAddressAndPassword(emailAddress, Password);
        }


        public Customer getCustomerByEmail(String emailAddress) {
            return customerRepository.findByEmailAddress(emailAddress);
        }


    }

