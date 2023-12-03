package cn.huicheng.shop.dao;

import cn.huicheng.shop.entities.CategorySecond;
import cn.huicheng.shop.entities.CategorySecondExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CategorySecondMapper {
    int countByExample(CategorySecondExample example);

    int deleteByExample(CategorySecondExample example);

    int deleteByPrimaryKey(Integer csid);

    int insert(CategorySecond record);

    int insertSelective(CategorySecond record);

    List<CategorySecond> selectByExample(CategorySecondExample example);

    CategorySecond selectByPrimaryKey(Integer csid);

    int updateByExampleSelective(@Param("record") CategorySecond record, @Param("example") CategorySecondExample example);

    int updateByExample(@Param("record") CategorySecond record, @Param("example") CategorySecondExample example);

    int updateByPrimaryKeySelective(CategorySecond record);

    int updateByPrimaryKey(CategorySecond record);

    /**
     * 根据一级类目的id获取对应的二级类目的列表
     * @param cid
     * @return
     */
	List<CategorySecond> findByCid(Integer cid);
}