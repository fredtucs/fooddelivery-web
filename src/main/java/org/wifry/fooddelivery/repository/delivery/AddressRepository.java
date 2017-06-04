package org.wifry.fooddelivery.repository.delivery;

import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Address;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {

}
