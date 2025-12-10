package com.pluralsight.NorthwindTradersAPI.dao.interfaces;

import com.pluralsight.NorthwindTradersAPI.models.Category;

import java.util.List;

public interface ICategoryDAO {

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);

    Category add(Category category);

    void update(int categoryId, Category category);

    void delete(int id);
}
