package com.powernode.transaction.service;

public interface AccountService {
    void transfer(String fromActno, String toActno, double money);
}
