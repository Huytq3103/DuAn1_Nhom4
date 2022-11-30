/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.service.ViewQuenMatKhauService;

/**
 *
 * @author qcuong
 */
public class ViewQuenMatKhauServiceImpl implements ViewQuenMatKhauService {

    private AccountRepository accountRepository = new AccountRepository();

    @Override
    public Account checkEmail(String email) {
        return accountRepository.checkEmail(email);
    }

    @Override
    public Boolean update(Account acc) {
        if (accountRepository.update(acc)) {
            return true;
        } else {
            return false;
        }
    }
}
