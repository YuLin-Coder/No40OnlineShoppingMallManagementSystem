package cn.huicheng.shop.service;

import java.util.List;

import cn.huicheng.shop.entities.Product;
import cn.huicheng.shop.utils.PageBean;

public interface ProductService {
	
	public List<Product> findHot();

	// 首页上最新商品的查询
	public List<Product> findNew();
	
	// 根据商品ID查询商品
	public Product findByPid(Integer pid);
	
	// 根据一级分类的cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page);

	// 根据二级分类查询商品信息
	public PageBean<Product> findByPageCsid(Integer csid, int page);
	
	// 后台查询所有商品带分页
	public PageBean<Product> findByPage(Integer page);

	// 业务层保存商品方法:
	public void save(Product product);
	
	// 业务层删除商品
	public void delete(Product product);

	// 业务层修改商品的方法
	public void update(Product product);

}
