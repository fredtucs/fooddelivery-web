package org.wifry.fooddelivery.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wifry.fooddelivery.model.Product;
import org.wifry.fooddelivery.services.admin.ProductService;
import org.wifry.fooddelivery.web.ws.model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_PRODUCTS)
@Scope("request")
public class ProductsResource {

    @Autowired
    private ProductService productService;

    private static final Logger LOG = LoggerFactory.getLogger(ProductsResource.class);

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductModel> getProducts(
            @RequestParam(name = "start", required = false) Integer start,
            @RequestParam(name = "size", required = false) Integer size) {

        LOG.debug("paginate parameter: start {} and size {}", start, size);

        List<ProductModel> pmList = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        if (start != null && size != null && start >= 0 && size >= 1) {
            products = productService.list();//listPaginate()findAllPaginated(start, size);
        } else {
            products = productService.list();
        }
        LOG.debug(" products ref size {} ", products.size());

        for (Product product : products) {
            pmList.add(new ProductModel(product.getProductId(), product.getName(), product.getProDescription(), product.getCurrentPrice()));
        }
        return pmList;

    }

}
