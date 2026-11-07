package com.powernode.springboot.dao;

import com.powernode.springboot.bean.Vip;

import java.util.List;

public interface VipMapper {
    int insert(Vip vip);

    List<Vip> selectAll();
}
