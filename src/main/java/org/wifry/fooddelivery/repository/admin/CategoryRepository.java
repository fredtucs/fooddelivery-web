
package org.wifry.fooddelivery.repository.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Category;
import org.wifry.fooddelivery.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE UPPER(c.categoryName) LIKE UPPER(CONCAT('%', ?1, '%') ) ORDER BY c.categoryName")
    List<Category> findCategory(String valor);

}
