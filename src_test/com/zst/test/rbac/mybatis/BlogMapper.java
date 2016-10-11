package com.zst.test.rbac.mybatis;

public interface BlogMapper {
	 //@Select("SELECT * FROM blog WHERE id = #{id}")
	  Blog selectBlog(int id);
}
class Blog{
	
}
