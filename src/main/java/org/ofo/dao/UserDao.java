package org.ofo.dao;

import org.ofo.entity.User;
import org.ofo.util.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 用户数据库操作类
public class UserDao extends BaseDao<User>{

    public UserDao(){
       tableName = "users";
    }
    @Override
    public void save(User user) {
        String sql = "insert into " + tableName + " values (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, IdGenerator.generate());
            statement.setString(2, user.getAccount());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "update " + tableName + " set account = ?," +
                " password = ?," +
                " role = ? " +
                "where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, user.getAccount());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setString(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> list() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                userList.add(user);
            }
            return userList;

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
    public User getById(String id) {
        String sql = "select * from " + tableName + " where id = '" + id + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql);){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                return user;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
