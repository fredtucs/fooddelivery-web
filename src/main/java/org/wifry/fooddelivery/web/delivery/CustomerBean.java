package org.wifry.fooddelivery.web.delivery;

import org.apache.commons.codec.digest.Md5Crypt;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.wifry.fooddelivery.exceptions.ChangeStatusException;
import org.wifry.fooddelivery.model.Address;
import org.wifry.fooddelivery.model.Customer;
import org.wifry.fooddelivery.model.Status;


import org.wifry.fooddelivery.services.delivery.CustomerService;
import org.wifry.fooddelivery.util.FacesUtils;
import org.wifry.fooddelivery.util.Md5Utils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by wtuco on 06/05/2016.
 *
 * @see Serializable
 */
@Component
@Scope("view")
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 3037619632050643265L;

    private List<Customer> customerList;
    private Customer customer;
    private Address address;
    private String valorBuscar;
    private String passwdtemp;

    @Autowired
    private CustomerService customerService;

    private ResourceBundle msg = FacesUtils.getBundle();

    @PostConstruct
    public void init() {
        valorBuscar = null;
        customer = new Customer();
    }

    public void listar() {
        setValorBuscar(null);
        customerList = customerService.listAll();
    }

    public void nuevo() {
        customer = new Customer();
        customer.setStatus(Status.ACTIVO);
        customer.setAddresses(new HashSet<>());
        FacesUtils.reset("fmEditCustomer");
    }

    public void editar() {
        if (customer != null && customer.getCustomerId() != 0L) {
            customer = customerService.getByID(customer.getCustomerId());
            passwdtemp = customer.getPassword();
            FacesUtils.reset("fmEditCustomer");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void buscar() {
        customerList = customerService.find(getValorBuscar());
    }

    public void eliminar() {
        try {
            String id = Faces.getRequestParameter("index");
            setCustomer(customerService.getByID(Long.valueOf(id)));
            customerService.delete(customer);
            listar();
            FacesUtils.addSuccessMessage(msg.getString("elimExito"));
            Ajax.update("fmCustomers");
            setCustomer(null);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errEliminar"));
            Ajax.update("fmCustomers:messages");
        }
    }

    public void guardar() {
        try {
            if (!ObjectUtils.isEmpty(customer.getPassword())) {
                customer.setPassword(Md5Utils.hash(customer.getPassword()));
            } else {
                customer.setPassword(passwdtemp);
            }
            customerService.save(customer);
            FacesUtils.addSuccessMessage(msg.getString("guardExito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errGuardar"));
            Ajax.update("fmCustomers:messages");
        }
    }

    public void editaEstado() throws ChangeStatusException {
        if (customer != null && customer.getCustomerId() != 0L) {
            Status status = customer.getStatus().isEActivo();
            customer.setStatus(status);
            customerService.updateState(customer);
            listar();
        }
    }

    public void listAddress() {
        if (customer != null && customer.getCustomerId() != 0L) {
            customer = customerService.findCustomerByIdWithAddress(customer.getCustomerId());
            FacesUtils.reset("fmListAddressCustomer");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void nuevoAddress() {
        address = new Address();
        address.setStatus(Status.ACTIVO);
        FacesUtils.reset("fmEditAddressCustomer");
    }

    public void guardarAddress() {
        try {
            address.setCustomer(customer);
            customerService.saveAddress(address);
            FacesUtils.addSuccessMessage(msg.getString("guardExito"));
            listAddress();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errGuardar"));
            Ajax.update("fmListAddressCustomer:messages");
        }
    }

    public void editAddress() {
        if (address != null && address.getAddressId() != 0L) {
            setAddress(customerService.getAddressByID(address.getAddressId()));
            FacesUtils.reset("fmEditAddressCustomer");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void eliminarAddress() {
        try {
            String id = Faces.getRequestParameter("index");
            setAddress(customerService.getAddressByID(Long.valueOf(id)));
            customerService.deleteAddress(address);
            listAddress();
            FacesUtils.addSuccessMessage(msg.getString("elimExito"));
            Ajax.update("fmListAddressCustomer");
            setAddress(null);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errEliminar"));
            Ajax.update("fmListAddressCustomer:messages");
        }
    }

    public Status[] getEstados() {
        return Status.valuesForm();
    }

	/*
     * Gets and Sets
	 */

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer finalidad) {
        this.customer = finalidad;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
