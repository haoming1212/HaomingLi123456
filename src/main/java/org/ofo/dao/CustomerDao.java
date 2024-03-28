package org.ofo.dao;

import org.ofo.entity.Customer;
import org.ofo.entity.User;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends BaseDao<Customer>{

    public CustomerDao(){
        this.tableName = "customers";
    }


    @Override
    public void save(Customer customer) {
        String sql = "insert into " + tableName + " values (?,?,?,?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, IdGenerator.generate());
            statement.setString(2, customer.getContact());
            statement.setString(3, customer.getReceiver());
            statement.setString(4, customer.getUserId());
            statement.setString(5, customer.getAddress());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "update " + tableName + " set contact = ?, receiver = ?, address = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, customer.getContact());
            statement.setString(2, customer.getReceiver());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getByUserId(String userId){
        String sql = "select * from " + tableName + " where user_id = '" + userId + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("id"));
                customer.setUserId(resultSet.getString("user_id"));
                customer.setAddress(resultSet.getString("address"));
                customer.setContact(resultSet.getString("contact"));
                customer.setReceiver(resultSet.getString("receiver"));
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
