package cn.huicheng.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huicheng.shop.dao.UserMapper;
import cn.huicheng.shop.entities.User;
import cn.huicheng.shop.service.UserService;
import cn.huicheng.shop.utils.MailUitls;
import cn.huicheng.shop.utils.PageBean;
import cn.huicheng.shop.utils.UUIDUtils;


/**
 * 用户名模块业务层代码
 *
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	// 注入UserMapper
	@Autowired
	private UserMapper userMapper ;
	
	// 按用户名查询用户的方法:
	public User checkByUsername(String username){
		return userMapper.selectByUsername(username);
	}

	// 业务层完成用户注册代码:
	public void save(User user) {
		// 将数据存入到数据库
		user.setState(0); // 0:代表用户未激活.  1:代表用户已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userMapper.insert(user);
		// 发送激活邮件;
		MailUitls.sendMail(user.getEmail(), code);
	}

	// 业务层根据激活码查询用户
	public User findByCode(String code) {
		
		List<User> users = userMapper.selectByCode(code);
		if(users != null && users.size() > 0){
			return users.get(0);
		}
		return null;
	}

	// 修改用户的状态的方法
	public void update(User existUser) {
		userMapper.updateByPrimaryKey(existUser);
	}

	// 用户登录的方法
	public User login(String username, String password) {
		return userMapper.login(username,password);
	}

	// 业务层用户查询所有
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		//totalCount = userMapper.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		//List<User> list = userMapper.findByPage(begin,limit);
		List<User> list = null;
		pageBean.setList(list);
		return pageBean;
	}


	public User findByUid(Integer uid) {
		return null;
		//return userMapper.findByUid(uid);
	}


	public void delete(Integer uid) {
		userMapper.deleteByPrimaryKey(uid);
	}

	@Override
	public void delete(User existUser) {
		// TODO Auto-generated method stub
		
	}
}
