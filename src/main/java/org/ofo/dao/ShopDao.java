package org.ofo.dao;

import org.ofo.entity.Shop;
import org.ofo.entity.User;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDao extends BaseDao<Shop>{

    public ShopDao(){
        tableName = "shops";
    }
    @Override
    public void save(Shop shop) {
        String sql = "insert into " + tableName + " values (?,?,?,?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, IdGenerator.generate());
            statement.setString(2, shop.getUserId());
            statement.setString(3, shop.getName());
            statement.setString(4, shop.getManager());
            statement.setString(5, shop.getPhone());
            statement.setString(6, shop.getAddress());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Shop shop) {
        String sql = "update " + tableName + " set name = ?," +
                " manager = ?," +
                " phone = ?, " +
                " address = ? " +
                "where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, shop.getName());
            statement.setString(2, shop.getManager());
            statement.setString(3, shop.getPhone());
            statement.setString(4, shop.getAddress());
            statement.setString(5, shop.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Shop> list() {
        List<Shop> shopList = new ArrayList<>();
        String sql = "select * from " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Shop shop = new Shop();
                shop.setId(resultSet.getString("id"));
                shop.setUserId(resultSet.getString("user_id"));
                shop.setName(resultSet.getString("name"));
                shop.setManager(resultSet.getString("manager"));
                shop.setPhone(resultSet.getString("phone"));
                shop.setAddress(resultSet.getString("address"));
                shopList.add(shop);
            }
            return shopList;

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
    public Shop getById(String id) {
        String sql = "select * from " + tableName + " where id = '" + id + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Shop shop = new Shop();
                shop.setId(resultSet.getString("id"));
                shop.setUserId(resultSet.getString("user_id"));
                shop.setName(resultSet.getString("name"));
                shop.setManager(resultSet.getString("manager"));
                shop.setPhone(resultSet.getString("phone"));
                shop.setAddress(resultSet.getString("address"));
                return shop;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
