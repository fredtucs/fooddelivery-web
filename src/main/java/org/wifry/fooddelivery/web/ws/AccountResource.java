package org.wifry.fooddelivery.web.ws;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_ACCOUNTS)
@Scope("request")
public class AccountResource {
}
