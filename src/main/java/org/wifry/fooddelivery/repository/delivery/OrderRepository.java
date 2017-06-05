
package org.wifry.fooddelivery.repository.delivery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wifry.fooddelivery.base.BaseRepository;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails od WHERE o.orderId = ?1 ")
    Order findOrderByIdWithOrderDetail(Long orderId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails od LEFT JOIN FETCH od.product p LEFT JOIN FETCH o.address a LEFT JOIN FETCH o.branch b LEFT JOIN FETCH o.customer c WHERE o.orderId = ?1 ")
    Order findOrderByIdView(Long orderId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.address a LEFT JOIN FETCH o.branch b LEFT JOIN FETCH o.customer c ORDER BY o.orderId")
    List<Order> findAll();

}
