package org.wifry.fooddelivery.web.admin;

import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wifry.fooddelivery.exceptions.ChangeStatusException;
import org.wifry.fooddelivery.model.Category;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.services.admin.CategoryService;
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
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 3037619632050646575L;

    private List<Category> categoryList;
    private Category category;
    private String valorBuscar;

    @Autowired
    private CategoryService categoryService;

    private ResourceBundle msg = FacesUtils.getBundle();

    @PostConstruct
    public void init() {
        valorBuscar = null;
        category = new Category();
    }

    public void listar() {
        setValorBuscar(null);
        categoryList = categoryService.listAll();
    }

    public void nuevo() {
        category = new Category();
        category.setStatus(Status.ACTIVO);
        FacesUtils.reset("fmEditCategory");
    }

    public void editar() {
        if (category != null && category.getCategoryId() != 0L) {
            category = categoryService.getByID(category.getCategoryId());
            FacesUtils.reset("fmEditCategory");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void buscar() {
        categoryList = categoryService.find(getValorBuscar());
    }

    public void eliminar() {
        try {
            String id = Faces.getRequestParameter("index");
            setCategory(categoryService.getByID(Long.valueOf(id)));
            categoryService.delete(category);
            listar();
            FacesUtils.addSuccessMessage(msg.getString("elimExito"));
            Ajax.update("fmCategorys");
            setCategory(null);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errEliminar"));
            Ajax.update("fmCategorys:messages");
        }
    }

    public void guardar() {
        try {
            categoryService.save(category);
            FacesUtils.addSuccessMessage(msg.getString("guardExito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errGuardar"));
            Ajax.update("fmCategorys:messages");
        }
    }

    public void editaEstado() throws ChangeStatusException {
        if (category != null && category.getCategoryId() != 0L) {
            Status status = category.getStatus().isEActivo();
            category.setStatus(status);
            categoryService.updateState(category);
            listar();
        }
    }

    public Status[] getEstados() {
        return Status.valuesForm();
    }

	/*
     * Gets and Sets
	 */

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> tipoCuentaList) {
        this.categoryList = tipoCuentaList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category finalidad) {
        this.category = finalidad;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }


}
