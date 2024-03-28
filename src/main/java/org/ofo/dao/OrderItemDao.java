package org.ofo.dao;

import org.ofo.entity.OrderItem;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao extends BaseDao<OrderItem>{

    public OrderItemDao(){
        this.tableName = "order_items";
    }


    @Override
    public void save(OrderItem orderItem) {
        String sql = "insert into " + tableName + " values (?,?,?,?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, IdGenerator.generate());
            statement.setString(2, orderItem.getItemId());
            statement.setInt(3, orderItem.getNumbers());
            statement.setBigDecimal(4, orderItem.getUnitPrice());
            statement.setString(5, orderItem.getImg());
            statement.setString(6, orderItem.getOrderId());
            statement.setString(7, orderItem.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderItem> listByOrderId(String orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "select * from " + tableName + " where order_id = '" + orderId + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(resultSet.getString("id"));
                orderItem.setItemId(resultSet.getString("item_id"));
                orderItem.setNumbers(resultSet.getInt("numbers"));
                orderItem.setImg(resultSet.getString("img"));
                orderItem.setOrderId(resultSet.getString("order_id"));
                orderItem.setUnitPrice(resultSet.getBigDecimal("unit_price"));
                orderItem.setName(resultSet.getString("name"));
                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
