package cn.huicheng.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.huicheng.shop.entities.Cart;
import cn.huicheng.shop.entities.CartItem;
import cn.huicheng.shop.entities.OrderItem;
import cn.huicheng.shop.entities.Orders;
import cn.huicheng.shop.entities.User;
import cn.huicheng.shop.service.OrdersService;
import cn.huicheng.shop.utils.PageBean;

@Controller
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	/**
	 * 根据用户id查询订单关联订单明细和商品明细
	 * 
	 * @author ghy
	 */
	@RequestMapping("/order_findByUid")
	public String orderFindByUid(HttpSession session, Map<String, Object> map,
			@RequestParam(required = false, defaultValue = "1") int page) {
		// 获得用户的id.
		User existUser = (User) session.getAttribute("existUser");
		// 获得用户的id
		Integer uid = existUser.getUid();
		// 根据用户的id查询订单:
		PageBean<Orders> pageBean = ordersService.findByUid(uid, page);
		// 将PageBean数据带到页面上.
		map.put("pageBean", pageBean);

		/*
		 * List<Orders> orderList = pageBean.getList(); for (Orders orders :
		 * orderList) { List<OrderItem> orderItems = orders.getOrderItems(); for
		 * (OrderItem orderItem : orderItems) { System.out.println(orderItem); }
		 * }
		 */
		return "orderList";
	}

	/**
	 * 生成订单
	 * @author ghy
	 */
	@RequestMapping("/order_saveOrder")
	public String saveOrders(HttpSession session,Map<String,Object> map) {

		// 调用Service完成数据库插入的操作:
		Orders order = new Orders();
		// 设置订单的总金额:订单的总金额应该是购物车中总金额:
		// 购物车在session中,从session中获得购物车信息.
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			//this.addActionMessage("亲!您还没有购物!");
			map.put("cartMsg", "亲!您还没有购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单的状态
		order.setState(1); // 1:未付款.
		// 设置订单时间
		order.setOrdertime(new Date());
		// 设置订单关联的客户:
		User existUser = (User) session.getAttribute("existUser");
		if (existUser == null) {
			map.put("loginMsg", "亲!您还没有登录，只有登录后才能购物!");
			return "msg";
		}
		order.setUser(existUser);
		order.setUid(existUser.getUid());
		//订单明细
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrders(order);
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		ordersService.save(order);
		// 清空购物车:
		cart.clearCart();
		
		// 页面需要回显订单信息:
		map.put("order", order);

		return "order";
	}
	
	

}
