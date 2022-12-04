/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.service.ViewLoginService;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

    public String validateLogin(JTextField username, JPasswordField pass) {
        if (username.getText().trim().isEmpty()) {
            return "Tài khoản không được trống";
        } else if (pass.getText().trim().isEmpty()) {
            return "Mật khẩu không được trống";
        } else if (username.getText().trim().length() < 8) {
            return "Tài khoản phải lớn hơn 8 kí tự";
        } else if (pass.getText().trim().length() < 8) {
            return "Mật khẩu phải lớn hơn 8 kí tự";
        } else {
            return " ";
        }

    }

}
