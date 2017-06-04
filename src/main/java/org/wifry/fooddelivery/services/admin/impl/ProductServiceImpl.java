package org.wifry.fooddelivery.services.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.wifry.fooddelivery.model.Product;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.repository.admin.ProductRepository;
import org.wifry.fooddelivery.services.admin.ProductService;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getByID(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> list() {
        Example<Product> example = Example.of(new Product(Status.ACTIVO));
        return productRepository.findAll(example);
    }

    @Override
    public List<Product> find(String valor) {
        return productRepository.findProduct(valor);
    }

    @Override
    public void save(Product entity) {
        productRepository.save(entity);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void updateState(Product entity) {
        productRepository.updateState(entity);
    }


}
