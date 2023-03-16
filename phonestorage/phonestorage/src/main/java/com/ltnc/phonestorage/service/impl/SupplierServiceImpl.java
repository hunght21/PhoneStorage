package com.ltnc.phonestorage.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltnc.phonestorage.entity.Supplier;
import com.ltnc.phonestorage.repository.SupplierRepository;
import com.ltnc.phonestorage.service.SupplierService;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    // sitas Spring Beans'as naudoja tik viena konstruktoriu,
    // todel mums nereikia naudoti @Autowire anotacijos
    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(Integer supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplierById(Integer supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
