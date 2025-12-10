package com.pluralsight.NorthwindTradersAPI.dao.implementaion;

import com.pluralsight.NorthwindTradersAPI.dao.interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class JDBCCategoryDAO implements ICategoryDAO {

    private DataSource dataSource;

    @Autowired
    public JDBCCategoryDAO(DataSource dataSource){
        this.dataSource = dataSource;
        initialize();
    }

    private void initialize(){
        try(Connection connection = dataSource.getConnection()){
            String createTableQuery = """
                    CREATE TABLE IF NOT EXIST categories
                    (CategoryID INT PRIMARY KEY AUTO_INCREMENT,
                    CategoryName VARCHAR(255) NOT NULL,
                    Description VARCHAR(255),
                    Picture VARCHAR(255))""";
            try(PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery)){
                createTableStatement.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return null;
    }
}
