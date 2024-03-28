package org.ofo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {

    protected String tableName;
    protected Connection connection;

    public BaseDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_food_order?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai", "root", "root1111" );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  void save(T t) {}

    public void update(T t) {}

    public List<T> list() {return new ArrayList<>();}

    public void delete(String id) {
        String sql = "delete from " + tableName + " where id = '" + id + "'";
        System.out.println(sql);
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T getById(String id) {return null;}
}
