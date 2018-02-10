package com.lc.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.lc.app.entity.UserEntity;


@Repository
public interface UserMapper {
	
	@Select("SELECT * FROM USER")
	@Results({ @Result(property = "sex", column = "SEX", javaType = String.class),
			@Result(property = "userName", column = "USER_NAME", javaType = String.class) })
	List<UserEntity> getAll();

	@Select("SELECT * FROM USER WHERE ID = #{id}")
	@Results({ @Result(property = "sex", column = "SEX", javaType = String.class),
			@Result(property = "userName", column = "USER_NAME", javaType = String.class) })
	UserEntity getOne(Long id);

	@Insert("INSERT INTO USER(USER_NAME, SEX) VALUES(#{userName}, #{sex})")
	void insert(UserEntity user);

	@Update("UPDATE USER SET USER_NAME=#{userName} WHERE ID =#{id}")
	void update(UserEntity user);

	@Delete("DELETE FROM USER WHERE ID =#{id}")
	void delete(Long id);

}
