package cn.huicheng.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.huicheng.shop.dao.OrdersMapper;
import cn.huicheng.shop.entities.Orders;
import cn.huicheng.shop.service.OrdersService;
import cn.huicheng.shop.utils.PageBean;

@Transactional
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Override
	public PageBean<Orders> findByUid(Integer uid, int page) {
		PageBean<Orders> pageBean = new PageBean<Orders>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = ordersMapper.findCountByUid(uid);
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
		List<Orders> list = ordersMapper.findPageByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(Orders order) {
		ordersMapper.insertGetId(order);
	}

	@Override
	public Orders findByOid(Integer oid) {
		return ordersMapper.selectByPrimaryKey(oid);
	}

}
