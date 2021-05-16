package com.zhj.abstractrouting.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    public DataSourceConfig() {}

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    private DataSource primaryDataSource;

    @Autowired
    private DataSource secondDataSource;

    @Bean
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("primaryDataSource", primaryDataSource);
        targetDataSource.put("secondDataSource", secondDataSource);

        DynamicDataSourceContextHolder.addDataSourceNames("primaryDataSource");
        DynamicDataSourceContextHolder.addDataSourceNames("secondDataSource");

        DynamicDataSource dataSource = new DynamicDataSource();
        //设置数据源映射
        dataSource.setTargetDataSources(targetDataSource);
        //设置默认数据源，当无法映射到数据源时会使用默认数据源
        dataSource.setDefaultTargetDataSource(primaryDataSource);
        dataSource.afterPropertiesSet();

        return dataSource;
    }

}
