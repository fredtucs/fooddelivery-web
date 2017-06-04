package org.wifry.fooddelivery.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wifry.fooddelivery.exceptions.NullPeriodoException;
import org.wifry.fooddelivery.exceptions.SaveEntityException;
import org.wifry.fooddelivery.model.*;
import org.wifry.fooddelivery.services.admin.BranchService;
import org.wifry.fooddelivery.services.admin.ProductService;
import org.wifry.fooddelivery.services.delivery.CustomerService;
import org.wifry.fooddelivery.services.delivery.OrderService;
import org.wifry.fooddelivery.web.ws.model.OrderCompleteModel;
import org.wifry.fooddelivery.web.ws.model.OrderMessageModel;
import org.wifry.fooddelivery.web.ws.model.OrderProductModel;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_ORDERS)
@Scope("request")
public class OrdersResource {

    private CustomerService customerService;
    private BranchService branchService;
    private ProductService productService;
    private OrderService orderService;

    private static final Logger LOG = LoggerFactory.getLogger(OrdersResource.class);

    @Autowired
    public OrdersResource(CustomerService customerService, BranchService branchService, ProductService productService, OrderService orderService) {
        this.customerService = customerService;
        this.branchService = branchService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderMessageModel putJson(@RequestBody OrderCompleteModel content, HttpServletResponse response) {

        LOG.debug("Address: {}, Branch: {}, Token: {} ", content.getAddressId(), content.getBranchName(), content.getToken());
        OrderMessageModel messageModel = new OrderMessageModel();
        try {

            Address a = customerService.getAddressByID(Long.parseLong(content.getAddressId()));
            Branch b = branchService.getByID(Long.parseLong(content.getBranchId()));
            Customer c = customerService.getByID(Long.parseLong(content.getCustomerId()));

            Set<OrderDetail> orderDetails = new HashSet<>();

            List<OrderProductModel> productModels = content.getSendProModels();
            int proCount = content.getSendProModels().size();
            LOG.debug("product count {}", proCount);

            Order order = new Order();
            order.setAddress(a);
            order.setBranch(b);
            order.setCustomer(c);

            Double totalPrice = 0D;
            for (OrderProductModel productModel : productModels) {
                Product p = productService.getByID(Long.parseLong(productModel.getProId()));
                totalPrice += p.getCurrentPrice().doubleValue();
                orderDetails.add(new OrderDetail(p, 1, p.getCurrentPrice(), Status.ACTIVO, order));
                LOG.debug("product {} with price {}", p.getName(), p.getCurrentPrice());
            }
            order.setCost(new BigDecimal(totalPrice));
            order.setOrderDetails(orderDetails);
            orderService.save(order);

            messageModel.setOrderMessage("Thank You. YourOrder has been received!");
            messageModel.setOrderId(order.getOrderId());
            messageModel.setOrderTime(new Date());
            messageModel.setTotalPrice(totalPrice);
            response.setStatus(HttpStatus.CREATED.value());
            return messageModel;
        } catch (SaveEntityException | NullPeriodoException e) {
            LOG.error("Error save order ", e);
            e.printStackTrace();
            messageModel.setOrderMessage("Error save your Order.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return messageModel;
        }
    }

}
