package cn.huicheng.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huicheng.shop.dao.CategoryMapper;
import cn.huicheng.shop.dao.CategorySecondMapper;
import cn.huicheng.shop.entities.Category;
import cn.huicheng.shop.entities.CategoryExample;
import cn.huicheng.shop.entities.CategorySecond;
import cn.huicheng.shop.service.CategoryService;


/**
 * 一级分类的业务层对象
 *
 */
@Transactional
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	// 注入CategoryMapper
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategorySecondMapper categorySecondMapper;

	// 业务层查询所有一级分类的方法
	public List<Category> findAll() {
		return categoryMapper.selectByExample(null);
	}
	// 业务层查询所有一级分类(包括二级类目)的方法
	public List<Category> findAllWithSecondCat() {
		List<Category> catList =  findAll();//获取一级类目
		//遍历一级类目, 获取每个一级类目的对应二级类目列表
		for(Category cat1 : catList){
			List<CategorySecond> cat2List = categorySecondMapper.findByCid(cat1.getCid());
			cat1.setCategorySeconds(cat2List);
		}
		return catList;
	}

	// 业务层保存一级分类的操作
	public void save(Category category) {
		categoryMapper.insert(category);
	}

	// 业务层根据一级分类id查询一级分类
	public Category findByCid(Integer cid) {
		return categoryMapper.selectByPrimaryKey(cid);
	}

	// 业务层删除一级分类
	public void delete(Integer cid) {
		categoryMapper.deleteByPrimaryKey(cid);
	}

	// 业务层修改一级分类
	public void update(Category category) {
		CategoryExample example = new CategoryExample();
		categoryMapper.updateByExample(category, example);
	}
	
}
