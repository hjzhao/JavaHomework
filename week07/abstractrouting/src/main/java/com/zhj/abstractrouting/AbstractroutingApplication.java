package com.zhj.abstractrouting;

import com.zhj.abstractrouting.dao.UserDao;
import com.zhj.abstractrouting.datasource.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AbstractroutingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AbstractroutingApplication.class, args);
		UserDao userDao = context.getBean(UserDao.class);
		userDao.queryUserById(1);
		userDao.queryPersonById(1);
	}

}
