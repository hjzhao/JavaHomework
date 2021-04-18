package com.zhj.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zhj")
@Data
public class ZhjProperties {
    private String name;
    private int age;
}
