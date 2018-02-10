package com.lc.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.lc.app.entity.UserEntity;
import com.lc.app.mapper.UserMapper;

/**
 * mybatis service
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.lc.app.mapper")
public class App implements CommandLineRunner
{
	@Autowired
	UserMapper userMapper;
	
    public static void main( String[] args )
    {
    		SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... paramVarArgs) throws Exception {
		userMapper.insert(new UserEntity("章颖", "女"));
		userMapper.insert(new UserEntity("李聪", "男"));
		userMapper.insert(new UserEntity("李儒志", "男"));
		userMapper.insert(new UserEntity("张晓红", "女"));
		userMapper.insert(new UserEntity("章建成", "男"));
		userMapper.insert(new UserEntity("徐丽芳", "女"));
	}
}
