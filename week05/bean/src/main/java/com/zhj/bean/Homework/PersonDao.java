package com.zhj.bean.Homework;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class PersonDao {
    public void run() {
        System.out.println("PersonDao run");
    }
}
