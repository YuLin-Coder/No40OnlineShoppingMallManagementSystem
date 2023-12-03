package cn.huicheng.shop.service;

import cn.huicheng.shop.entities.Orders;
import cn.huicheng.shop.utils.PageBean;

public interface OrdersService {

	PageBean<Orders> findByUid(Integer uid, int page);

	void save(Orders order);
	
	Orders findByOid(Integer oid);

}
