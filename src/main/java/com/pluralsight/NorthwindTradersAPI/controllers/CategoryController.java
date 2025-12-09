package com.pluralsight.NorthwindTradersAPI.controllers;


import com.pluralsight.NorthwindTradersAPI.models.Product;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private List<Category> categories;

    public CategoryController(){
        categories = new ArrayList<>();

        categories.add(new Category(1,"Beverages"));
        categories.add(new Category(2,"Wine"));

    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Category> getProducts(){
        return categories;
    }

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable int categoryId){
        for (Category category:categories){
            if(category.getCategoryId() == categoryId){
                return category;
            }
        }
        return null;
    }

}
