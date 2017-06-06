package org.wifry.fooddelivery.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wifry.fooddelivery.model.Address;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.services.delivery.CustomerService;
import org.wifry.fooddelivery.web.ws.model.AddressModel;
import org.wifry.fooddelivery.web.ws.model.GetCustomerAddressModel;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_CUSTOMERS)
@Scope("request")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    private static final Logger LOG = LoggerFactory.getLogger(ProductsResource.class);

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveTokenFcm(@RequestBody String input, HttpServletResponse response) {


        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
