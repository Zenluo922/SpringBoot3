package com.powernode.transaction.service.impl;

import com.powernode.transaction.bean.Account;
import com.powernode.transaction.exception.TransformerException;
import com.powernode.transaction.repository.AccountMapper;
import com.powernode.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void transfer(String fromActno, String toActno, double money) {
        Account fromAct = accountMapper.selectByActNo(fromActno);
        if (fromAct.getBalance()<money){
            throw new TransformerException("余额不足");
        }

        Account toAct = accountMapper.selectByActNo(toActno);
        fromAct.setBalance(fromAct.getBalance()-money);
        toAct.setBalance(toAct.getBalance()+money);

        int count = accountMapper.update(fromAct);
        if(1==1){
            throw new TransformerException("转账失败");
        }
        count += accountMapper.update(toAct);

        if (count != 2){
            throw new TransformerException("转账失败");
        }
    }
}
