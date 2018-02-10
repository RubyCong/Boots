package com.lc.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public interface MybatisService<T> {
	public void save(T t);
	public void delete(String ids);
	public void update(T t);
	public List<T> find(Long id);
}
