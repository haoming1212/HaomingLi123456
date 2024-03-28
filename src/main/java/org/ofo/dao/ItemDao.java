package org.ofo.dao;

import org.ofo.entity.Item;
import org.ofo.entity.User;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends BaseDao<Item>{


    public ItemDao(){
        tableName = "items";
    }
    @Override
    public void save(Item item) {
        String sql = "insert into " + tableName + " values (?,?,?,?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, IdGenerator.generate());
            statement.setString(2, item.getShopId());
            statement.setString(3, item.getName());
            statement.setBigDecimal(4, item.getPrice());
            statement.setString(5, item.getImg());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Item item) {
        String sql = "update " + tableName + " set name = ?," +
                " price = ?," +
                " img = ? " +
                "where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, item.getName());
            statement.setBigDecimal(2, item.getPrice());
            statement.setString(3, item.getImg());
            statement.setString(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> list() {
        List<Item> itemList = new ArrayList<>();
        String sql = "select * from " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setImg(resultSet.getString("img"));
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
    public Item getById(String id) {
        String sql = "select * from " + tableName + " where id = '" + id + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setImg(resultSet.getString("img"));
                return item;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
