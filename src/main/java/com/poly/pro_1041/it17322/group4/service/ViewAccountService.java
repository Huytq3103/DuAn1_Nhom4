/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.response.ViewAccountReponse;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ViewAccountService {

    List<ViewAccountReponse> getAll();

    String add(ViewAccountReponse vaccr);

    String update(ViewAccountReponse vaccr);

    List<ViewAccountReponse> searchByName(List<ViewAccountReponse> lists, String hoTen);
}
