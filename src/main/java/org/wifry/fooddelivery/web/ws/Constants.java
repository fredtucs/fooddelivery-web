package org.wifry.fooddelivery.web.ws;

import java.nio.charset.Charset;

public final class Constants {

    /**
     * prefix of REST API
     */
    public static final String URI_API = "/api";

    public static final String URI_PRODUCTS = "/products";

    public static final String URI_LOGIN = "/login";

    public static final String URI_BRANCHES = "/branches";

    public static final String URI_ADDRESSES = "/addresses";

    public static final String URI_CUSTOMERS = "/customers";

    public static final String URI_ACCOUNTS = "/accounts";

    public static final String URI_ORDERS = "/orders";


    /**
     * Encoding
     */
    public static final Charset ISO88591 = Charset.forName("iso-8859-1");
    public static final Charset WINDOWS1252 = Charset.forName("windows-1252");
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final String ENCODING_DEFAULT = "windows-1252";

    private Constants() {
        throw new InstantiationError("Must not instantiate this class");
    }

}
