package com.zhj.bean.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DemoConfiguration.class)
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    @Autowired
    DemoConfiguration demoConfiguration;

    @Autowired
    DemoProperties demoProperties;

    @Bean(name="demoInfo")
    public DemoInfo getDemoInfo() {
        return new DemoInfo(demoProperties.getHello());
    }
}
