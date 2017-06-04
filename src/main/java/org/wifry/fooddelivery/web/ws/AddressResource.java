package org.wifry.fooddelivery.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wifry.fooddelivery.model.Address;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.services.delivery.CustomerService;
import org.wifry.fooddelivery.web.ws.model.AddressModel;
import org.wifry.fooddelivery.web.ws.model.BranchModel;
import org.wifry.fooddelivery.web.ws.model.GetCustomerAddressModel;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_ADDRESSES)
@Scope("request")
public class AddressResource {

    @Autowired
    private CustomerService customerService;

    private static final Logger LOG = LoggerFactory.getLogger(ProductsResource.class);

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<AddressModel> getJson(@RequestBody GetCustomerAddressModel input, HttpServletResponse response) {

        Long id = (null != input.getId()) ? Long.parseLong(input.getId()) : -1L;
        if (!customerService.confirmToken(input.getEmail(), input.getToken()) || id < 1) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            LOG.error("BAD REQUEST ID, EMAIL OR TOKEN  ERROR");
            return null;
        }

        Customer customer = customerService.findCustomerByIdWithAddress(id);
        List<Address> addresses = new ArrayList<>(customer.getAddresses());
        List<AddressModel> addressModels = new ArrayList<>();
        addresses.stream().forEach((address) -> {
            try {
                addressModels.add(new AddressModel(id, address.getAddressId(), address.getDistrict(), address.getAddresstext()));
            } catch (Exception e) {
                LOG.error(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });

        response.setStatus(HttpStatus.ACCEPTED.value());
        return addressModels;
    }

}
