package com.zhj.jdbc;

import com.zhj.jdbc.dao.UserDao;
import com.zhj.jdbc.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication
@ImportResource(value = "classpath:Spring-Module.xml")
public class JdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JdbcApplication.class, args);
		UserDao userDao = (UserDao) context.getBean("userDao");

		List<User> userList = userDao.generateUsers(1000000);
        System.out.println("generate end");

		userDao.batchInsert(userList);
	}

}
