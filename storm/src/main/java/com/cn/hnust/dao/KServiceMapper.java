package com.cn.hnust.dao;

import com.cn.hnust.pojo.KService;


public interface KServiceMapper {
    int deleteByPrimaryKey(String kfAccount);

    int insert(KService record);

    int insertSelective(KService record);

    KService selectByPrimaryKey(String kfAccount);

    int updateByPrimaryKeySelective(KService record);

    int updateByPrimaryKey(KService record);
}