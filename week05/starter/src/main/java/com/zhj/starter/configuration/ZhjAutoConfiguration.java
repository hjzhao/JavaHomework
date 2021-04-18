package com.zhj.starter.configuration;

import com.zhj.starter.properties.ZhjProperties;
import com.zhj.starter.service.ZhjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZhjProperties.class)
@ConditionalOnProperty(prefix = "zhj", name = "info", havingValue = "true")
public class ZhjAutoConfiguration {
    private ZhjProperties zhjProperties;

    @Autowired
    public void setZhjProperties(ZhjProperties zhjProperties) {
        this.zhjProperties = zhjProperties;
    }

    @Bean
    public ZhjService zhjService() {
        return new ZhjService(zhjProperties.getName(), zhjProperties.getAge());
    }
}
