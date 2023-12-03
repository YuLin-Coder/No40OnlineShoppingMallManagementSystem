package cn.huicheng.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huicheng.shop.dao.ProductMapper;
import cn.huicheng.shop.entities.Product;
import cn.huicheng.shop.entities.ProductExample;
import cn.huicheng.shop.entities.ProductExample.Criteria;
import cn.huicheng.shop.service.ProductService;
import cn.huicheng.shop.utils.PageBean;

/**
 * 商品的业务层代码
 * 
 * @author 传智.郭嘉
 * 
 */
@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {
	// 注入ProductMapper
	@Autowired
	private ProductMapper productMapper;

	// 首页上热门商品查询
	public List<Product> findHot() {
		return productMapper.selectHot();
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		return productMapper.selectNew();
	}

	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return productMapper.selectByPrimaryKey(pid);
	}

	// 根据一级分类的cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		//totalCount = productMapper.findCountCid(cid);
		totalCount = productMapper.selectCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		//List<Product> list = productMapper.findByPageCid(cid, begin, limit);
		List<Product> list = productMapper.selectByPageCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 根据二级分类查询商品信息
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		//totalCount = productMapper.findCountCsid(csid);
		totalCount = productMapper.selectCountCsid(csid);
		
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		//List<Product> list = productMapper.findByPageCsid(csid, begin, limit);
		List<Product> list = productMapper.selectByPageCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 后台查询所有商品带分页
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		ProductExample example = new ProductExample();
		totalCount = productMapper.countByExample(example);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productMapper.selectByPage(begin, limit);
		
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层保存商品方法:
	public void save(Product product) {
		productMapper.insert(product);
	}

	// 业务层删除商品
	public void delete(Integer pid) {
		productMapper.deleteByPrimaryKey(pid);
	}

	// 业务层修改商品的方法
	public void update(Product product) {
		ProductExample example = new ProductExample();
		productMapper.updateByExample(product, example);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		
	}

}
