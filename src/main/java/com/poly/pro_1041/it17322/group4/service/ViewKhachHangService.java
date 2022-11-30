/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author DELL
 */
public interface ViewKhachHangService {

    List<ViewKhachHangRepose> getAll();

    KhachHang getOne(String id);

    String add(ViewKhachHangRepose vkhr);

    String update(ViewKhachHangRepose vkhr);

    String delete(ViewKhachHangRepose vkhr);

    Account getOneNguoiTao(String id);

    List<ViewKhachHangRepose> search(List<ViewKhachHangRepose> lists, String hoTen);
}
