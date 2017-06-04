
package org.wifry.fooddelivery.repository.delivery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.addresses a WHERE c.customerId = ?1 ")
    Customer findCustomerByIdWithAddress(Long id);

    List<Customer> findByNameIgnoreCaseContaining(String name);
    
    

}
