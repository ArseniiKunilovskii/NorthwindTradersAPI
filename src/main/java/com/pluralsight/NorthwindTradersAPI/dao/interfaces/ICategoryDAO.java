package com.pluralsight.NorthwindTradersAPI.dao.interfaces;

import com.pluralsight.NorthwindTradersAPI.models.Category;

import java.util.List;

public interface ICategoryDAO {

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);
}
