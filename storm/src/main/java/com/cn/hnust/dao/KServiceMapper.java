package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface KServiceMapper<T> {
    int deleteByPrimaryKey(String kfAccount);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String kfAccount);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
    List<T> queryAll();
    
    List<T> queryTByPageSize(@Param(value = "startRow") int startRow,@Param(value = "pageSize") int pageSize);
}