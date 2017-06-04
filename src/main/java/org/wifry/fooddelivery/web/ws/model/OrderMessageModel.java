package org.wifry.fooddelivery.web.ws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.DateFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderMessageModel {

    private Long orderId;
    private Double totalPrice;
    private Date orderTime;
    private String orderMessage;

    public OrderMessageModel() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderMessageModel setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public OrderMessageModel setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.S")
    public Date getOrderTime() {
        return orderTime;
    }

    public OrderMessageModel setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public OrderMessageModel setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
        return this;
    }

}
