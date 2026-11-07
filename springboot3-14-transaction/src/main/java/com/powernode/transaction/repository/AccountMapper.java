package com.powernode.transaction.repository;

import com.powernode.transaction.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account selectByActNo(String actNo);

    int update(Account account);
}
