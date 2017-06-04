package org.wifry.fooddelivery.services.delivery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wifry.fooddelivery.model.Address;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.repository.delivery.AddressRepository;
import org.wifry.fooddelivery.repository.delivery.CustomerRepository;
import org.wifry.fooddelivery.services.delivery.CustomerService;

import java.util.List;
import org.wifry.fooddelivery.util.Md5Utils;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Customer getByID(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer findCustomerByIdWithAddress(Long id) {
        return customerRepository.findCustomerByIdWithAddress(id);
    }

    @Override
    public List<Customer> listAll() {
        return customerRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Customer> list() {
        Example<Customer> example = Example.of(new Customer(Status.ACTIVO));
        return customerRepository.findAll(example);
    }

    @Override
    public List<Customer> find(String valor) {
        return customerRepository.findByNameIgnoreCaseContaining(valor);
    }

    @Override
    public void save(Customer entity) {
        customerRepository.save(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerRepository.delete(entity);
    }

    @Override
    public void updateState(Customer entity) {
        customerRepository.updateState(entity);
    }

    @Override
    public boolean confirmToken(String token) {
        Example<Customer> example = Example.of(new Customer(null, null, token));
        return customerRepository.exists(example);
    }

    @Override
    public boolean confirmToken(String email, String token) {
        Example<Customer> example = Example.of(new Customer(email, null, token));
        return customerRepository.exists(example);
    }

    @Override
    public boolean confirmEmailPassword(String email, String password) {
        Example<Customer> example = Example.of(new Customer(email, Md5Utils.hash(password), null));
        return customerRepository.exists(example);
    }

    @Override
    public Customer findByMailAndPass(String email, String password) {
        Example<Customer> example = Example.of(new Customer(email, Md5Utils.hash(password), null));
        return customerRepository.findOne(example);
    }

    /**
     * Maintenance Address
     */
    /**
     * @param id
     * @return
     */
    @Override
    public Address getAddressByID(Long id) {
        return addressRepository.findOne(id);
    }

    /**
     * Save address entity
     *
     * @param entity
     */
    @Override
    public void saveAddress(Address entity) {
        addressRepository.save(entity);
    }

    /**
     * Delete address entity
     * @param entity 
     */
    @Override
    public void deleteAddress(Address entity) {
        addressRepository.delete(entity);
    }

}
