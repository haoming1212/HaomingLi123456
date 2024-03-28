package org.ofo.dao;

import org.ofo.entity.Order;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao<Order> {

    public OrderDao(){
        tableName = "orders";
    }
    @Override
    public void save(Order item) {
        String sql = "insert into " + tableName + " values (?,?,?,?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, item.getId());
            statement.setString(2, "已支付");
            statement.setString(3, item.getShopId());
            statement.setString(4, item.getUserId());
            statement.setString(5, item.getReceiver());
            statement.setString(6, item.getPhone());
            statement.setString(7, item.getAddress());
            statement.setBigDecimal(8, item.getMoney());
            statement.setString(9, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order item) {
        String sql = "update " + tableName + " set receiver = ?," +
                " phone = ?," +
                " address = ?, " +
                " status = ?" +
                "where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, item.getReceiver());
            statement.setString(2, item.getPhone());
            statement.setString(3, item.getAddress());
            statement.setString(4, item.getStatus());
            statement.setString(5, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> list() {
        List<Order> itemList = new ArrayList<>();
        String sql = "select * from " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order item = new Order();
                item.setId(resultSet.getString("id"));
                item.setShopId(resultSet.getString("shop_id"));
                item.setPhone(resultSet.getString("phone"));
                item.setReceiver(resultSet.getString("receiver"));
                item.setAddress(resultSet.getString("address"));
                item.setUserId(resultSet.getString("user_id"));
                item.setStatus(resultSet.getString("status"));
                item.setMoney(resultSet.getBigDecimal("money"));
                item.setCreateTime(resultSet.getString("create_time"));
                itemList.add(item);
            }
            return itemList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    public Order getById(String id) {
        String sql = "select * from " + tableName + " where id = " + id;
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order item = new Order();
                item.setId(resultSet.getString("id"));
                item.setShopId(resultSet.getString("shop_id"));
                item.setPhone(resultSet.getString("phone"));
                item.setReceiver(resultSet.getString("receiver"));
                item.setAddress(resultSet.getString("address"));
                item.setUserId(resultSet.getString("user_id"));
                item.setStatus(resultSet.getString("status"));
                item.setMoney(resultSet.getBigDecimal("money"));
                item.setCreateTime(resultSet.getString("create_time"));
                return item;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
