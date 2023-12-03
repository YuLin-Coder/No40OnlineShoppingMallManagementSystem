package cn.huicheng.shop.dao;

import cn.huicheng.shop.entities.Product;
import cn.huicheng.shop.entities.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> selectByPage(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

	List<Product> selectByPageCsid(@Param("csid") Integer csid, @Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

	int selectCountCsid(Integer csid);

	List<Product> selectByPageCid(@Param("cid") Integer cid, @Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

	int selectCountCid(Integer cid);

	List<Product> selectNew();

	List<Product> selectHot();
}