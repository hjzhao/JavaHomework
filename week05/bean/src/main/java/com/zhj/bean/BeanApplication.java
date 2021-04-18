package com.zhj.bean;


import com.zhj.bean.Configuration.DemoInfo;
import com.zhj.bean.Service.PersonFactory;
import com.zhj.bean.Service.PersonService;
import com.zhj.bean.Util.SpringUtil;
import com.zhj.bean.Homework.Person;
import com.zhj.bean.Homework.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BeanApplication {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		//xml
        System.out.println("--------------- xml Person1");
		Person person = (Person) context.getBean("Person1");
		System.out.println(person.getName() + " " + person.getAge());

		//xml constructor
        System.out.println("--------------- xml Person2");
		Person person2 = (Person) context.getBean("Person2");
		System.out.println(person2.getName() + " " + person2.getAge());

        System.out.println("--------------- xml PersonService");
		PersonService personService = (PersonService) context.getBean("PersonService");
		personService.personDaoRun();

        //注解context
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(PersonFactory.class);
        annotationConfigApplicationContext.refresh();
        System.out.println("--------------- Annotation p1");
        PersonDao p1 = (PersonDao) annotationConfigApplicationContext.getBean("p1");
        p1.run();


		ApplicationContext context1 = SpringApplication.run(BeanApplication.class, args);

        System.out.println("--------------- run Context getBean PersonService");
        PersonService personService1 = context1.getBean(PersonService.class);
        personService1.personDaoRun();

        System.out.println("--------------- run Context getBean PersonDao");
        PersonDao personDao = context1.getBean(PersonDao.class);
        personDao.run();


        //--------  SpringUtil  ---------
        //autowired 放到 run后面调用
        System.out.println("--------------- custom util getBean PersonService");
        PersonService personService2 = SpringUtil.getApplicationContext().getBean(PersonService.class);
        personService2.personDaoRun();

        System.out.println("--------------- custom util getBean PersonDao");
        PersonDao personDao1 = SpringUtil.getApplicationContext().getBean(PersonDao.class);
        personDao1.run();

    }

    @Autowired
    DemoInfo demoInfo;

	@Bean
    public void printInfo() {
        System.out.println(demoInfo.getData());
    }

}
