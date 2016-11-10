package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface ArticleMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
    
    List<T> selectAll();
    
    List<T> selectByPage(@Param(value = "startRow") int startRow,@Param(value = "pageSize") int pageSize);
}