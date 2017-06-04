package org.wifry.fooddelivery.services.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.wifry.fooddelivery.model.Branch;
import org.wifry.fooddelivery.model.Status;
import org.wifry.fooddelivery.repository.admin.BranchRepository;
import org.wifry.fooddelivery.services.admin.BranchService;

import java.util.List;

@Service("branchService")
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch getByID(Long id) {
        return branchRepository.findOne(id);
    }

    @Override
    public List<Branch> listAll() {
        return branchRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Branch> list() {
        Example<Branch> example = Example.of(new Branch(Status.ACTIVO));
        return branchRepository.findAll(example);
    }

    @Override
    public List<Branch> find(String valor) {
        return branchRepository.findBranch(valor);
    }

    @Override
    public void save(Branch entity) {
        branchRepository.save(entity);
    }

    @Override
    public void delete(Branch entity) {
        branchRepository.delete(entity);
    }

    @Override
    public void updateState(Branch entity) {
        branchRepository.updateState(entity);
    }

}
