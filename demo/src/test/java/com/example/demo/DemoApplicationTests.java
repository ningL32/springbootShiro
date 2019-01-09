package com.example.demo;

import com.example.demo.pojo.playIdiom.PlayIdiom;
import com.example.demo.service.playIdiom.PlayIdiomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.example.demo.dao")
public class DemoApplicationTests {

	@Autowired
    private PlayIdiomService playIdiomService;
	@Test
	public void contextLoads() {
      System.out.println("sys--hello");
	}
	@Test
	public void insertIdiom(){
		PlayIdiom playIdiom = new PlayIdiom();
		playIdiom.setIdiom("百尺竿头");
		try {
			if(playIdiomService.insert(playIdiom) == true){
				System.out.println("sys--hello");
			} else {
				System.out.println("sys22--hello");
			}

		}catch (Exception e){
			e.printStackTrace();
		}

	}

}

