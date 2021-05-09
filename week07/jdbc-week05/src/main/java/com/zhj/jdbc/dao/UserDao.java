package com.zhj.jdbc.dao;

import com.zhj.jdbc.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<User> generateUsers(int number) {
        List<User> userList = new ArrayList<>();

        char[] cs = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            String name = "";
            for (int j = 0; j < 10; j++) {
                int rand = random.nextInt(cs.length - 1);
                name += cs[rand];
            }

            User user = new User();
            user.setName(name);
            user.setAge(random.nextInt(50));
            userList.add(user);
            System.out.println(i);
        }
        return userList;
    }

    public void batchInsert(List<User> userList) {
        String sql = "insert into user (name, age) values (?, ?)";

        long start = System.currentTimeMillis();

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            // 批量插入时ps对象必须放到for循环外面
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < userList.size(); i++) {
                long subStart = System.currentTimeMillis();

                ps.setString(1, userList.get(i).getName());
                ps.setInt(2, userList.get(i).getAge());
                ps.execute();
                long subEnd = System.currentTimeMillis();
                //System.out.println(subEnd - subStart);
                if (i % 10000 == 0) {
                    long end = System.currentTimeMillis();
                    System.out.println(end - start);
                }
            }

        } catch (Exception e){
            log.error("sql exception", e);
        } finally {
            if (Objects.nonNull(conn)) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void insert(User user) {
        String sql = "insert into user (name, age) value (?, ?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(conn)) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User queryUserById(int userId) {
        String sql = "select * from user where id = ?";
        Connection connection = null;
        ResultSet resultSet = null;

        User user = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
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
        return user;
    }
}
