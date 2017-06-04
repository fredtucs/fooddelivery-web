package org.wifry.fooddelivery.web.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.services.delivery.CustomerService;
import org.wifry.fooddelivery.util.TokenUtil;
import org.wifry.fooddelivery.web.ws.model.LoginModel;
import org.wifry.fooddelivery.web.ws.model.LoginSendModel;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.wifry.fooddelivery.exceptions.NullPeriodoException;
import org.wifry.fooddelivery.exceptions.SaveEntityException;

@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_LOGIN)
@Scope("request")
public class LoginResource {

    @Autowired
    private CustomerService customerService;

    private static final Logger LOG = LoggerFactory.getLogger(LoginResource.class);

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginSendModel getLogin(@RequestBody LoginModel input, HttpServletResponse response) {

        LOG.debug("Send from Movil data Email: {} and Passwd: {} ", input.getEmail(), input.getPassword());

        if (input.getEmail() == null || input.getEmail().equals("") || input.getPassword() == null || input.getPassword().isEmpty()) {
            LOG.error("nullable problem");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        Customer customer = customerService.findByMailAndPass(input.getEmail(), input.getPassword());
        if (customer == null) {
            LOG.error("Database has retuned null customer");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
        String newToken = TokenUtil.getToken(input.getEmail(), input.getPassword());
        customer.setToken(newToken);
        try {
            customerService.save(customer);
            response.setHeader("AuthHeader", newToken);
            response.setStatus(HttpStatus.ACCEPTED.value());
            LOG.debug("new token: {} ", newToken);
            LOG.debug("found customer in Database: {}", customer.getEmail());
            return new LoginSendModel(customer.getCustomerId(), customer.getEmail());
        } catch (NullPeriodoException | SaveEntityException e) {
            LOG.error("Error edit customer", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> getLogintoken(@RequestHeader("AuthHeader") String token, @RequestBody LoginModel input, HttpServletResponse response) {

        LOG.debug("Send from Movil data Email: {} , Passwd: {} and token {} ", input.getEmail(), input.getPassword(), token);

        if (input.getEmail() == null || input.getEmail().equals("") || input.getPassword() == null || input.getPassword().isEmpty()
                || token == null || token.isEmpty()) {
            LOG.debug("header token: {0} ", token);
            LOG.error("nullable problem");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!customerService.confirmToken(token)) {
            LOG.error("confirm token error");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Customer customer = customerService.findByMailAndPass(input.getEmail(), input.getPassword());
        if (customer == null) {
            LOG.error("Database has retuned null customer");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String newToken = TokenUtil.getToken(input.getEmail(), input.getPassword());
        customer.setToken(newToken);
        try {
            customerService.save(customer);
            response.setHeader("AuthHeader", newToken);
            LOG.debug("regenerate token {}", newToken);
            LOG.debug("found customer in Database: {}", customer.getEmail());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NullPeriodoException | SaveEntityException e) {
            LOG.error("Error edit customer", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
