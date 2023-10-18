package com.example.springstore.controllers;

import com.example.springstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
@Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model){
    model.addAttribute("products",productService.findAll());
    return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable("id") Long id, Principal principal){
    if(principal== null){
        return "redirect:/products";
    }
    productService.addToUserBucket(id, principal.getName());
    return "redirect:/products";
    }
}
