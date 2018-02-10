package com.lc.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lc.app.entity.UserEntity;
import com.lc.app.util.MessageUtil;
import com.lc.app.mapper.UserMapper;

public class MybatisServiceImpl implements MybatisService<UserEntity> {
	
	@Autowired
	UserMapper userMapper;

	@Override
	@RequestMapping(value="/save", method = RequestMethod.PUT)
	public void save(UserEntity t) {
		userMapper.insert(t);
	}

	@RequestMapping(value="/delete", method = RequestMethod.DELETE)
	public void delete(String ids) {
		if(ids == null) {
			throw new RuntimeException(MessageUtil.getMsgByCode(1));
		}
		String[] idArray = ids.split(",");
		for (String idStr : idArray) {
			Long id;
			try {
				id = Long.valueOf(idStr);
			} catch (Exception e) {
				throw new RuntimeException(MessageUtil.getMsgByCode(2));
			}
			userMapper.delete(id);
		}
	}

	@RequestMapping(value="/update", method = RequestMethod.POST)
	public void update(UserEntity t) {
		userMapper.update(t);
	}

	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public List<UserEntity> find(@PathVariable(value="id") Long id) {
		if(id == null) {
			return userMapper.getAll();
		}else {
			List<UserEntity> list = new ArrayList<UserEntity>();
			list.add(userMapper.getOne(id));
			return list;
		}
	}

}
