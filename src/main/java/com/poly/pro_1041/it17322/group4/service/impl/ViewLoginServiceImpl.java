/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.service.ViewLoginService;

/**
 *
 * @author Huy PC
 */
public class ViewLoginServiceImpl implements ViewLoginService {

    private AccountRepository ar = new AccountRepository();

    @Override
    public Account getOne(String username, String pass) {
        return ar.getOne(username, pass);
    }

}
