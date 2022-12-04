/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Huy PC
 */
public interface ViewLoginService {

    Account getOne(String username, String pass);

    String validateLogin(JTextField username, JPasswordField pass);
}
