package cn.huicheng.shop.service;

import java.util.List;

import cn.huicheng.shop.entities.Category;

public interface CategoryService {

	// 业务层查询所有一级分类的方法
	public List<Category> findAll();
	
	// 业务层查询所有一级分类(包含二级类目)的方法
	public List<Category> findAllWithSecondCat();

	// 业务层保存一级分类的操作
	public void save(Category category);

	// 业务层根据一级分类id查询一级分类
	public Category findByCid(Integer cid);

	// 业务层删除一级分类
	public void delete(Integer cid);

	// 业务层修改一级分类
	public void update(Category category);

}
