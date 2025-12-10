package com.pluralsight.NorthwindTradersAPI.controllers;


import com.pluralsight.NorthwindTradersAPI.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController implements IProductDAO {
    private List<Product> products;

    public ProductsController(){
        products = new ArrayList<>();

        products.add(new Product(1,"Pepsi",1,2.5));
        products.add(new Product(2,"Coke",1,2.5));
        products.add(new Product(3,"Red Wine",2,6));
        products.add(new Product(4,"White Wine",2,6));
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return products;
    }

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int productId){
        for (Product product:products){
            if(product.getProductId() == productId){
                return product;
            }
        }
        return null;
    }

}
