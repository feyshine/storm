package com.cn.hnust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.hnust.pojo.WxMenu;

public interface WxMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxMenu record);

    int insertSelective(WxMenu record);

    WxMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxMenu record);

    int updateByPrimaryKey(WxMenu record);
    
    public List<WxMenu> queryWxMenuByPage(@Param(value = "startRow") int startRow,@Param(value = "pageSize") int pageSize);
    
    public List<WxMenu> queryAll();
}