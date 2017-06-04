
package org.wifry.fooddelivery.repository.admin;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Product;
import org.wifry.fooddelivery.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE UPPER(CONCAT('%', ?1, '%') ) ORDER BY p.name")
    List<Product> findProduct(String valor);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category c ORDER BY p.name")
    List<Product> listAll();


}
