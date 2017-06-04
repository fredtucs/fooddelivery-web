
package org.wifry.fooddelivery.repository.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Branch;
import org.wifry.fooddelivery.model.Branch;

import java.util.List;

@Repository
public interface BranchRepository extends BaseRepository<Branch, Long> {

    @Query("SELECT b FROM Branch b WHERE UPPER(b.name) LIKE UPPER(CONCAT('%', ?1, '%') ) ORDER BY b.name")
    List<Branch> findBranch(String valor);

}
