package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ComplexButtonMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);
    
    List<T> queryAll();
    
    List<T> queryByPage(@Param(value = "startRow") int startRow,@Param(value = "pageSize") int pageSize);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}