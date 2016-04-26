package com.cn.hnust.dao;

import java.util.List;

import com.cn.hnust.pojo.Button;

public interface ButtonMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);
    
    T selectByParams(Long id,String parentid);
    
    List<T> selectByParent(String parentid);
    
    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}