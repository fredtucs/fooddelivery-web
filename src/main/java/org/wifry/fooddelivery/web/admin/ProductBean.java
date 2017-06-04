package org.wifry.fooddelivery.web.admin;

import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wifry.fooddelivery.exceptions.ChangeStatusException;
import org.wifry.fooddelivery.model.Category;
import org.wifry.fooddelivery.model.Product;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.services.admin.CategoryService;
import org.wifry.fooddelivery.services.admin.ProductService;
import org.wifry.fooddelivery.util.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by wtuco on 06/05/2016.
 *
 * @see Serializable
 */
@Component
@Scope("view")
public class ProductBean implements Serializable {

    private static final long serialVersionUID = 3037619632050646689L;

    private List<Product> productList;
    private List<Category> categoryList;
    private Product product;
    private String valorBuscar;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private ResourceBundle msg = FacesUtils.getBundle();

    @PostConstruct
    public void init() {
        valorBuscar = null;
        product = new Product();
    }

    public void listar() {
        setValorBuscar(null);
        setProductList(productService.listAll());
        setCategoryList(categoryService.list());
    }

    public void nuevo() {
        product = new Product();
        product.setStatus(Status.ACTIVO);
        product.setCategory(new Category());
        FacesUtils.reset("fmEditProduct");
    }

    public void editar() {
        if (product != null && product.getProductId() != 0L) {
            product = productService.getByID(product.getProductId());
            FacesUtils.reset("fmEditProduct");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void buscar() {
        productList = productService.find(getValorBuscar());
    }

    public void eliminar() {
        try {
            String id = Faces.getRequestParameter("index");
            setProduct(productService.getByID(Long.valueOf(id)));
            productService.delete(product);
            listar();
            FacesUtils.addSuccessMessage(msg.getString("elimExito"));
            Ajax.update("fmProducts");
            setProduct(null);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errEliminar"));
            Ajax.update("fmProducts:messages");
        }
    }

    public void guardar() {
        try {
            productService.save(product);
            FacesUtils.addSuccessMessage(msg.getString("guardExito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errGuardar"));
            Ajax.update("fmProducts:messages");
        }
    }

    public void editaEstado() throws ChangeStatusException {
        if (product != null && product.getProductId() != 0L) {
            Status status = product.getStatus().isEActivo();
            product.setStatus(status);
            productService.updateState(product);
            listar();
        }
    }

    public Status[] getEstados() {
        return Status.valuesForm();
    }

	/*
     * Gets and Sets
	 */

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> tipoCuentaList) {
        this.productList = tipoCuentaList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product finalidad) {
        this.product = finalidad;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }


    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
