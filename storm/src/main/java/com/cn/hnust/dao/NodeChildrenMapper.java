package com.cn.hnust.dao;

import java.util.List;


public interface NodeChildrenMapper<T> {
    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
	
	List<T> queryChildNodes(String pid);
}