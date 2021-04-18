package com.zhj.bean.Service;

import com.zhj.bean.Homework.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonDao personDao;

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void personDaoRun() {
        personDao.run();
    }
}
