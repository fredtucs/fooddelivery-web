package org.wifry.fooddelivery.web.delivery;

import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wifry.fooddelivery.exceptions.ChangeStatusException;
import org.wifry.fooddelivery.model.Branch;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.model.Order;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.services.delivery.OrderService;
import org.wifry.fooddelivery.util.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by wtuco on 06/05/2016.
 *
 * @see Serializable
 */
@Component
@Scope("view")
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 3037619632050646575L;

    private List<Order> orderList;
    private Order order;
    private String valorBuscar;

    @Autowired
    private OrderService orderService;

    private ResourceBundle msg = FacesUtils.getBundle();

    @PostConstruct
    public void init() {
        valorBuscar = null;
        order = new Order();
    }

    public void listar() {
        setValorBuscar(null);
        orderList = orderService.listAll();
    }

    public void nuevo() {
        order = new Order();
        order.setStatus(Status.ACTIVO);
        FacesUtils.reset("fmEditOrder");
    }

    public void editar() {
        if (order != null && order.getOrderId() != 0L) {
            order = orderService.getByID(order.getOrderId());
            FacesUtils.reset("fmEditOrder");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void buscar() {
        orderList = orderService.find(getValorBuscar());
    }

    public void eliminar() {
        try {
            String id = Faces.getRequestParameter("index");
            setOrder(orderService.getByID(Long.valueOf(id)));
            orderService.delete(order);
            listar();
            FacesUtils.addSuccessMessage(msg.getString("elimExito"));
            Ajax.update("fmOrders");
            setOrder(null);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errEliminar"));
            Ajax.update("fmOrders:messages");
        }
    }

    public void guardar() {
        try {
            orderService.save(order);
            FacesUtils.addSuccessMessage(msg.getString("guardExito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errGuardar"));
            Ajax.update("fmOrders:messages");
        }
    }

    public void editaEstado() throws ChangeStatusException {
        if (order != null && order.getOrderId() != 0L) {
            Status status = order.getStatus().isEActivo();
            order.setStatus(status);
            orderService.updateState(order);
            listar();
        }
    }

    public void doValueChangeQuantity(ValueChangeEvent event) {
        Object newValue = event.getNewValue();
        if (newValue != null) {

        }
    }

    public List<Branch> autoComplete(String query) {
//        if (beneficiariosList != null) {
//            return beneficiariosList.stream()
//                    .filter(s -> s.toUpperCase().contains(String.valueOf(query).toUpperCase()))
//                    .collect(Collectors.toList());
//        }
        return new ArrayList<>();
    }

    public List<Customer> autoCompleteCustomer(String query) {
//        if (beneficiariosList != null) {
//            return beneficiariosList.stream()
//                    .filter(s -> s.toUpperCase().contains(String.valueOf(query).toUpperCase()))
//                    .collect(Collectors.toList());
//        }
        return new ArrayList<>();
    }

    public Status[] getEstados() {
        return Status.valuesForm();
    }

	/*
     * Gets and Sets
	 */

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> tipoCuentaList) {
        this.orderList = tipoCuentaList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order finalidad) {
        this.order = finalidad;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }


}
