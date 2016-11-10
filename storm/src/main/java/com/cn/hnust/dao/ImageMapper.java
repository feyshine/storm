package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface ImageMapper<T> {
    int deleteByPrimaryKey(Long imgId);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long imgId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
    List<T> selectAll();
    
    List<T> selectByPage(@Param(value = "startRow") int startRow,@Param(value = "pageSize") int pageSize);
}