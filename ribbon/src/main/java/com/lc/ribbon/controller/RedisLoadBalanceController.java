package com.lc.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RedisLoadBalanceController {
	
    @Value("${redis.server.url}")
    private String REDIS_SERVER_URL;
	
	@Autowired
	RestTemplate template;
	
	@RequestMapping(value="/put/{key}/{value}", method = RequestMethod.PUT)
	public void put(@PathVariable(name = "key") String key, @PathVariable(name = "value") String value) {
		template.put(REDIS_SERVER_URL+"/put/{key}/{value}", null, key, value);
	}
	
	@RequestMapping(value="/get/{key}", method = RequestMethod.GET)
	@ResponseBody
	public String get(@PathVariable(name = "key") String key) {
		return template.getForObject(REDIS_SERVER_URL+"/get/{key}", String.class, key);
	}
	
	@RequestMapping(value="/del/{key}", method = RequestMethod.DELETE)
	public void del(@PathVariable(name = "key") String key) {
		template.delete(REDIS_SERVER_URL+"/del/{key}", key);
	}
	
}
