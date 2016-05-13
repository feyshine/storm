package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface FansMapper<T> {
    int deleteByPrimaryKey(String openid);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
    
    List<T> selectAll();
    
    List<T> selectByPage(@Param(value = "startRow") int startRow,@Param(value = "pageSize") int pageSize);
}