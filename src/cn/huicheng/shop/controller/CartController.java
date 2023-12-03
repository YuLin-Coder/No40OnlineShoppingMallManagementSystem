package cn.huicheng.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.huicheng.shop.entities.Cart;
import cn.huicheng.shop.entities.CartItem;
import cn.huicheng.shop.entities.Product;
import cn.huicheng.shop.entities.User;
import cn.huicheng.shop.service.ProductService;

/**
 * 购物车相关
 */
@Controller
public class CartController {
	// 注入商品的Service
	@Autowired
	private ProductService productService;
	
	public CartController() {
		System.out.println("CartController");
	}

	// 将购物项添加到购物车:执行的方法
	@RequestMapping("cart_addCart")
	public String addCart(Integer pid, int count, HttpSession session) {
		// 封装一个CartItem对象.
		CartItem cartItem = new CartItem();
		// 设置数量:
		cartItem.setCount(count);
		// 根据pid进行查询商品:
		Product product = productService.findByPid(pid);
		// 设置商品:
		cartItem.setProduct(product);
		// 将购物项添加到购物车.
		// 购物车应该存在session中.
		Cart cart = getCart(session);
		cart.addCart(cartItem);

		return "cart";
	}

	// 清空购物车的执行的方法:
	@RequestMapping("/cart_clearCart")
	public String clearCart(HttpSession session){
		// 获得购物车对象.
		Cart cart = getCart(session);
		// 调用购物车中清空方法.
		cart.clearCart();
		return "cart";
	}
	
	// 从购物车中移除购物项的方法:
	@RequestMapping("/cart_removeCart")
	public String removeCart(HttpSession session,@RequestParam("pid") Integer pid){
		// 获得购物车对象
		Cart cart = getCart(session);
		// 调用购物车中移除的方法:
		cart.removeCart(pid);
		// 返回页面:
		return "redirect:/cart_myCart";
	}
	
	// 我的购物车:执行的方法
	@RequestMapping("/cart_myCart")
	public String myCart(HttpSession session,Map<String,Object> map){
		
		User existUser = (User) session.getAttribute("existUser");
		if (existUser == null) {
			map.put("loginMsg", "亲!您还没有登录，只有登录后才能购物!");
			return "msg";
		}		
		
		return "cart";
	}
	
	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * @return
	 */
	private Cart getCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
