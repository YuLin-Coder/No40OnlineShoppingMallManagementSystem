package cn.huicheng.shop.service;

import cn.huicheng.shop.entities.User;
import cn.huicheng.shop.utils.PageBean;

public interface UserService {

	//查询用户名是否存在
	public User checkByUsername(String username);

	// 业务层完成用户注册代码:
	public void save(User user);

	// 业务层根据激活码查询用户
	public User findByCode(String code);

	// 修改用户的状态的方法
	public void update(User existUser);

	// 用户登录的方法
	public User login(String username, String password);

	// 业务层用户查询所有
	public PageBean<User> findByPage(Integer page);

	public User findByUid(Integer uid);

	public void delete(User existUser);
	
	public void delete(Integer uid);
	
}
