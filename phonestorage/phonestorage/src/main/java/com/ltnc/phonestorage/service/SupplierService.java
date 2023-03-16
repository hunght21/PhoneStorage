package com.ltnc.phonestorage.service;

import com.ltnc.phonestorage.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    Supplier saveSupplier(Supplier supplier);

    Supplier getSupplierById(Integer supplierId);

    Supplier updateSupplier(Supplier supplier);

    void deleteSupplierById(Integer supplierId);


}
