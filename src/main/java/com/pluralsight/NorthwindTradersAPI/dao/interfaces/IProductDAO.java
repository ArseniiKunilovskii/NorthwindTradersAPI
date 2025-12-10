package com.pluralsight.NorthwindTradersAPI.dao.interfaces;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface IProductDAO {

    List<Product> getProducts();

    Product getProductById(int productId);
}
