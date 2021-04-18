package com.zhj.bean.Service;

import com.zhj.bean.Homework.PersonDao;
import org.springframework.context.annotation.Bean;

public class PersonFactory {
    @Bean(name="p1")
    public PersonDao getPsersonDao() {
        return new PersonDao();
    }
}
