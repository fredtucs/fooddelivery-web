package org.wifry.fooddelivery.web.admin;

import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wifry.fooddelivery.exceptions.ChangeStatusException;
import org.wifry.fooddelivery.model.Branch;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.services.admin.BranchService;
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
public class BranchBean implements Serializable {

    private static final long serialVersionUID = 3037619632050643265L;

    private List<Branch> branchList;
    private Branch branch;
    private String valorBuscar;

    @Autowired
    private BranchService branchService;

    private ResourceBundle msg = FacesUtils.getBundle();

    @PostConstruct
    public void init() {
        valorBuscar = null;
        branch = new Branch();
    }

    public void listar() {
        setValorBuscar(null);
        branchList = branchService.listAll();
    }

    public void nuevo() {
        branch = new Branch();
        branch.setStatus(Status.ACTIVO);
        FacesUtils.reset("fmEditBranch");
    }

    public void editar() {
        if (branch != null && branch.getBranchId() != 0L) {
            branch = branchService.getByID(branch.getBranchId());
            FacesUtils.reset("fmEditBranch");
        } else {
            FacesUtils.addWarnigMessage(msg.getString("errEmptyObj"));
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void buscar() {
        branchList = branchService.find(getValorBuscar());
    }

    public void eliminar() {
        try {
            String id = Faces.getRequestParameter("index");
            setBranch(branchService.getByID(Long.valueOf(id)));
            branchService.delete(branch);
            listar();
            FacesUtils.addSuccessMessage(msg.getString("elimExito"));
            Ajax.update("fmBranchs");
            setBranch(null);
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errEliminar"));
            Ajax.update("fmBranchs:messages");
        }
    }

    public void guardar() {
        try {
            branchService.save(branch);
            FacesUtils.addSuccessMessage(msg.getString("guardExito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
            FacesUtils.addErrorMessage(msg.getString("errGuardar"));
            Ajax.update("fmBranchs:messages");
        }
    }

    public void editaEstado() throws ChangeStatusException {
        if (branch != null && branch.getBranchId() != 0L) {
            Status status = branch.getStatus().isEActivo();
            branch.setStatus(status);
            branchService.updateState(branch);
            listar();
        }
    }

    public Status[] getEstados() {
        return Status.valuesForm();
    }

	/*
     * Gets and Sets
	 */

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> tipoCuentaList) {
        this.branchList = tipoCuentaList;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch finalidad) {
        this.branch = finalidad;
    }

    public String getValorBuscar() {
        return valorBuscar;
    }

    public void setValorBuscar(String valorBuscar) {
        this.valorBuscar = valorBuscar;
    }


}
