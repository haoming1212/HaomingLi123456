package org.ofo.dao;

import org.ofo.entity.Cart;
import org.ofo.entity.User;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao extends BaseDao<Cart>{


    public CartDao (){
        this.tableName = "carts";
    }

    @Override
    public void save(Cart cart) {
        String sql = "insert into " + tableName + " values (?,?,?,?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, IdGenerator.generate());
            statement.setString(2, cart.getItemId());
            statement.setString(3, cart.getName());
            statement.setBigDecimal(4, cart.getUnitPrice());
            statement.setInt(5, cart.getNumber());
            statement.setString(6, cart.getUserId());
            statement.setString(7, cart.getImg());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(String id) {
        super.delete(id);
    }



    public Cart getByUserAndItem(String userId, String itemId) {
        String sql = "select * from " + tableName + " where user_id = '" + userId + "' and item_id = '" + itemId + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getString("id"));
                cart.setName(resultSet.getString("name"));
                cart.setUnitPrice(resultSet.getBigDecimal("unit_price"));
                cart.setNumber(resultSet.getInt("number"));
                cart.setUserId(resultSet.getString("user_id"));
                cart.setItemId(resultSet.getString("item_id"));
                cart.setImg(resultSet.getString("img"));
                return cart;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Cart getById(String id) {
        String sql = "select * from " + tableName + " where id = '" + id + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getString("id"));
                cart.setName(resultSet.getString("name"));
                cart.setUnitPrice(resultSet.getBigDecimal("unit_price"));
                cart.setNumber(resultSet.getInt("number"));
                cart.setUserId(resultSet.getString("user_id"));
                cart.setItemId(resultSet.getString("item_id"));
                cart.setImg(resultSet.getString("img"));
                return cart;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cart> list() {
        List<Cart> cartList = new ArrayList<>();
        String sql = "select * from " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getString("id"));
                cart.setName(resultSet.getString("name"));
                cart.setUnitPrice(resultSet.getBigDecimal("unit_price"));
                cart.setNumber(resultSet.getInt("number"));
                cart.setUserId(resultSet.getString("user_id"));
                cart.setItemId(resultSet.getString("item_id"));
                cart.setImg(resultSet.getString("img"));
                cartList.add(cart);
            }
            return cartList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public void update(Cart cart) {
        String sql = "update " + tableName + " set `number` = ? " +
                " where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1, cart.getNumber());
            statement.setString(2, cart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
