
package org.wifry.fooddelivery.repository.admin;

import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Branch;

@Repository
public interface BucketRepository extends BaseRepository<Branch, Long> {

}
