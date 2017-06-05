package org.wifry.fooddelivery.services.delivery.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wifry.fooddelivery.model.Order;
import org.wifry.fooddelivery.repository.delivery.OrderRepository;
import org.wifry.fooddelivery.services.delivery.OrderService;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getByID(Long id) {
        return orderRepository.findOrderByIdWithOrderDetail(id);
    }

    @Override
    public Order getOrderByIDForView(Long id) {
        return orderRepository.findOrderByIdView(id);
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public void save(Order entity) {
        orderRepository.save(entity);
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
    public void updateState(Order entity) {
        orderRepository.updateState(entity);
    }

}
