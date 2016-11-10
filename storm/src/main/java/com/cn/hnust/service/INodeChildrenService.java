package com.cn.hnust.service;

import java.util.List;


public interface INodeChildrenService<T> {
	
	
    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
	
	List<T> queryChildNodes(String pid);

}
