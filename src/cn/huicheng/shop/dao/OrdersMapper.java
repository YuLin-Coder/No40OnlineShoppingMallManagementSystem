package cn.huicheng.shop.dao;

import cn.huicheng.shop.entities.Orders;
import cn.huicheng.shop.entities.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

	int findCountByUid(Integer uid);

	List<Orders> findPageByUid(@Param("uid") Integer uid, @Param("begin") Integer begin, @Param("limit")int limit);
	
	int insertGetId(Orders order);
	
}