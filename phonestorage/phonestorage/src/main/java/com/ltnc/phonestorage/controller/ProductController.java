package com.ltnc.phonestorage.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ltnc.phonestorage.entity.Product;
import com.ltnc.phonestorage.entity.Supplier;
import com.ltnc.phonestorage.service.ProductService;
import com.ltnc.phonestorage.service.SupplierService;
import com.ltnc.phonestorage.utils.ImageUpload;

import jakarta.validation.Valid;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;


import javax.sql.rowset.serial.SerialException;

@Controller
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final SupplierService supplierService;

    @Autowired
    private final ImageUpload imageUpload;





    public ProductController(ProductService productService, SupplierService supplierService, ImageUpload imageUpload) {
        this.productService = productService;
        this.supplierService = supplierService;
        this.imageUpload = imageUpload;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());

        return "products";

    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";

    }

    @PostMapping("/products")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                            BindingResult bindingResult,
                                @RequestParam("file") MultipartFile file) throws IOException {
                           
        if(file == null){
            product.setImage(null);
        }else{
            if(imageUpload.uploadImage(file)){
                System.out.println("Upload successfully");
            }
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        if(bindingResult.hasErrors()){
            return "create_product";
        }
        productService.saveProduct(product,file);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable(value = "id") Long partNumberId, Model model) {
        model.addAttribute("product", productService.getProductById(partNumberId));
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", allSuppliers);
        return "edit_product";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable(value = "id") Long partNumberId,
                                @ModelAttribute("product") Product product,
                                @RequestParam("file") MultipartFile file,
                                BindingResult bindingResult,
                                Model model) throws IOException, SerialException, SQLException {
        Product existingProduct = productService.getProductById(partNumberId);

        
        if(file == null){
            existingProduct.setImage(product.getImage());
        }else{
            if(imageUpload.checkExisted(file) == false){
                imageUpload.uploadImage(file);
            }
            existingProduct.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        }

        existingProduct.setPartNumberId(partNumberId);
        existingProduct.setPartDescription(product.getPartDescription());
        existingProduct.setPartQty(product.getPartQty());
        existingProduct.setPartCost(product.getPartCost());
        existingProduct.setPartRetailPrice(product.getPartRetailPrice());

        Supplier supplier = new Supplier();
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", allSuppliers);
        supplier.setSupplierId(product.getBrandId());
        existingProduct.setSupplier(supplier);

        productService.updateProduct(existingProduct,file);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long partNumberId) {
        productService.deleteProductById(partNumberId);
        return "redirect:/products";
    }
}
