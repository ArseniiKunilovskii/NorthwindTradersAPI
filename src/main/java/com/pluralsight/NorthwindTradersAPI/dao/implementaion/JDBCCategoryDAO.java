package com.pluralsight.NorthwindTradersAPI.dao.implementaion;



import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCategoryDAO implements ICategoryDAO {

    private final DataSource dataSource;

    @Autowired
    public JDBCCategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> Categories = new ArrayList<>();
        String sql = "SELECT * FROM Categories";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int CategoryID = resultSet.getInt("CategoryID");
                String CategoryName = resultSet.getString("CategoryName");
                Category Category = new Category(CategoryID,CategoryName);
                Categories.add(Category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Categories;
    }

    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM Categories WHERE CategoryID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int CategoryID = resultSet.getInt("CategoryID");
                    String CategoryName = resultSet.getString("CategoryName");
                    Category Category = new Category(CategoryID,CategoryName);
                    return Category;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Category add(Category Category) {
        String sql = "INSERT INTO Categories (CategoryID, CategoryName) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, Category.getCategoryId());
            statement.setString(2, Category.getCategoryName());


            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Category failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    Category.setCategoryId(generatedId);
                } else {
                    throw new SQLException("Creating Category failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Category;
    }

    @Override
    public void update(int id, Category Category) {
        String sql = "UPDATE Categories SET CategoryID = ?,CategoryName = ? WHERE CategoryID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, Category.getCategoryId());
            statement.setString(2, Category.getCategoryName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Categories WHERE CategoryID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}