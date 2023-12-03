package cn.huicheng.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huicheng.shop.entities.Product;
import cn.huicheng.shop.service.CategoryService;
import cn.huicheng.shop.service.ProductService;
import cn.huicheng.shop.utils.PageBean;

/**
 * 商品相关
 */
@Controller
public class ProductController {
	// 注入商品的Service
	@Autowired
	private ProductService productService;

	// 注入一级分类的Service
	@Autowired
	private CategoryService categoryService;
	
	// 根据商品的ID进行查询商品:执行方法:
	@RequestMapping("product_findByPid")
	public String findByPid(Integer pid, Model model) {
		// 调用Service的方法完成查询.
		Product product = productService.findByPid(pid);
		model.addAttribute("model", product);
		return "product";
	}
	
	// 根据一级分类的id查询商品:
	@RequestMapping("product_findByCid")
	public String findByCid(Integer cid, Integer page, Model model) {
		if(page == null){//默认查询第一页的数据
			page = 1;
		}
		// List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);// 根据一级分类查询商品,带分页查询
		// 将PageBean存入Model中:
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("cid", cid);
		return "productList";
	}

	// 根据二级分类id查询商品:
	@RequestMapping("product_findByCsid")
	public String findByCsid(Integer csid, Integer page, Model model) {
		if(page == null){//默认查询第一页的数据
			page = 1;
		}
		// 根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		// 将PageBean存入Model中:
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("csid", csid);
		return "productList";
	}
}
