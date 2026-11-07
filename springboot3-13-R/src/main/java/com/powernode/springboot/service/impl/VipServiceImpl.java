package com.powernode.springboot.service.impl;

import com.powernode.springboot.bean.Vip;
import com.powernode.springboot.dao.VipMapper;
import com.powernode.springboot.service.VipService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipServiceImpl implements VipService {

    @Resource
    private VipMapper vipMapper;
    @Override
    public boolean saveVip(Vip vip) {
        return vipMapper.insert(vip) ==1;
    }

    @Override
    public List<Vip> findAll() {
        return vipMapper.selectAll();
    }
}
