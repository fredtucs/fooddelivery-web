package org.wifry.fooddelivery.services.delivery;

import org.wifry.fooddelivery.model.Address;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.services.BaseService;


public interface CustomerService extends BaseService<Customer> {

    Customer findCustomerByIdWithAddress(Long id);

    Address getAddressByID(Long id);

    void saveAddress(Address entity);

    void deleteAddress(Address entity);

    boolean confirmToken(String token);

    boolean confirmToken(String email, String token);

    boolean confirmEmailPassword(String email, String password);

    Customer findByMailAndPass(String email, String password);

}
