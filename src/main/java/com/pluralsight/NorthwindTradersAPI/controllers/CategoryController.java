package com.pluralsight.NorthwindTradersAPI.controllers;


import com.pluralsight.NorthwindTradersAPI.dao.implementaion.JDBCCategoryDAO;
import com.pluralsight.NorthwindTradersAPI.dao.implementaion.JDBCProductDAO;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController implements ICategoryDAO {
    private final ICategoryDAO categoryDAO;

    public CategoryController(ICategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(path = "/category", method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        return categoryDAO.getAllCategories();
    }

    @RequestMapping(path = "/category/{categoryId}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable int categoryId){
       return categoryDAO.getCategoryById(categoryId);
    }

    @RequestMapping(path = "/category", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category add(Category category) {
        return categoryDAO.add(category);
    }

    @RequestMapping(path = "/category/{categoryId}", method = RequestMethod.PUT)
    public void update(@PathVariable int categoryId, @RequestBody Category category){
        categoryDAO.update(categoryId, category);
    }

    @RequestMapping(path = "/category/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        categoryDAO.delete(id);
    }

}
