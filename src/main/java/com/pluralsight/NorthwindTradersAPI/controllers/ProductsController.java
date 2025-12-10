package com.pluralsight.NorthwindTradersAPI.controllers;


import com.pluralsight.NorthwindTradersAPI.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController{
    private final IProductDAO productDAO;

    public ProductsController(IProductDAO productDAO){
    this.productDAO = productDAO;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productDAO.getProducts();
    }

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int productId){
       return  productDAO.getProductById(productId);
    }

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productDAO.add(product);
    }

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.PUT)
    public void update(@PathVariable int productId, @RequestBody Product product){
        productDAO.update(productId, product);
    }

    @RequestMapping(path = "/produts/{id}", method = RequestMethod.DELETE)
    public void update(@PathVariable int id){
        productDAO.delete(id);
    }

}
