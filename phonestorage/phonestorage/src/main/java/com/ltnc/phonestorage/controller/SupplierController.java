package com.ltnc.phonestorage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ltnc.phonestorage.entity.Supplier;
import com.ltnc.phonestorage.service.SupplierService;

@Controller
public class SupplierController {

    @Autowired
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers";
    }

    @GetMapping("/suppliers/new")
    public String createSupplierForm(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("supplier", supplier);
        return "create_supplier";
    }

    @PostMapping("/suppliers")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.saveSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String editSupplierForm(@PathVariable(value = "id") Integer supplierId, Model model) {
        model.addAttribute("supplier", supplierService.getSupplierById(supplierId));
        return "edit_supplier";

    }

    @PostMapping("/suppliers/{id}")
    public String updateSupplier(@PathVariable(value = "id") Integer supplierId,
                                 @ModelAttribute("supplier") Supplier supplier,
                                 Model model) {

        Supplier existingSupplier = supplierService.getSupplierById(supplierId);
        existingSupplier.setSupplierId(supplierId);
        existingSupplier.setSupplierBrand(supplier.getSupplierBrand());

        supplierService.updateSupplier(existingSupplier);
        return "redirect:/suppliers";
    }


    @GetMapping("/suppliers/{id}")
    public String deleteSupplier(@PathVariable(value = "id") Integer supplierId) {
        supplierService.deleteSupplierById(supplierId);
        return "redirect:/suppliers";
    }
}





