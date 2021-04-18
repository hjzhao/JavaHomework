package com.zhj.starter;

import com.zhj.starter.service.ZhjService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class StarterApplicationTests {

	@Autowired
	private ZhjService zhjService;

	@Test
	void contextLoads() {
		System.out.println(zhjService.info());
	}

}
