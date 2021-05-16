package com.zhj.abstractrouting.dao;

import com.zhj.abstractrouting.datasource.DynamicDataSource;
import com.zhj.abstractrouting.datasource.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Service
public class UserDao {

    @Autowired
    private DataSource dataSource;

    public void queryUserById(int userId) {
        DynamicDataSourceContextHolder.setDataSourceName("primaryDataSource");

        String sql = "select * from user where id = ?";
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (Objects.nonNull(resultSet)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void queryPersonById(int personId) {
        DynamicDataSourceContextHolder.setDataSourceName("secondDataSource");

        String sql = "select * from person where id = ?";
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, personId);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("nickname") + " " + resultSet.getString("sex") + " " + resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(connection)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (Objects.nonNull(resultSet)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
