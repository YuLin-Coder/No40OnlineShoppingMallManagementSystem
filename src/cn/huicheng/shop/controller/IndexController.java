package cn.huicheng.shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huicheng.shop.entities.Category;
import cn.huicheng.shop.entities.Product;
import cn.huicheng.shop.service.CategoryService;
import cn.huicheng.shop.service.ProductService;

/**
 * 首页访问的 Controller
 *
 */
@Controller
public class IndexController {
	// 注入一级分类的Service:
	@Autowired
	private CategoryService categoryService;
	// 注入商品的Service
	@Autowired
	private ProductService productService;
	

	/**
	 * 执行的访问首页的方法:
	 */
	@RequestMapping("/index")
	public String index(HttpSession session,Map<String, Object> map){
		
		//System.out.println("index....");
		
		// 查询所有一级分类(包含对应的二级分类列表)集合
		List<Category> cList = categoryService.findAllWithSecondCat();
		// 将一级分类存入到Session的范围:
		session.setAttribute("cList", cList);
		// 查询热门商品:
		List<Product> hList = productService.findHot();
		// 保存到值栈中:
		map.put("hList", hList);
		// 查询最新商品:
		List<Product> nList = productService.findNew();
		// 保存到值栈中:
		map.put("nList", nList);
		return "index";
	}
	
	
}
