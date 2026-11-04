package com.powernode.springboot.service;

import com.powernode.springboot.bean.Vip;

import java.util.List;

public interface VipService {
    boolean saveVip(Vip vip);

    List<Vip> findAll();
}
