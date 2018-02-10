package com.lc.redis.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface RedisService {
	
	@RequestMapping(value="/put/{key}/{value}", method = RequestMethod.PUT)
	public void put(@PathVariable(name = "key") String key, @PathVariable(name = "value") String value);
	
	@RequestMapping(value="/get/{key}", method = RequestMethod.GET)
	@ResponseBody
	public String get(@PathVariable(name = "key") String key);
	
	@RequestMapping(value="/del/{key}", method = RequestMethod.DELETE)
	public void del(@PathVariable(name = "key") String key);
	
}
