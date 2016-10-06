package com.zs.test.busi;

import java.util.Map;

public interface UserMapper {
	public User getUser(int id);
	public User getUserByAge(int age);
	public User getUserDulParmMap(Map<String, Object> map);
	public User getUserDulParmBean(User user);
	public void insertUserByMap(Map<String, Object> map);
}
