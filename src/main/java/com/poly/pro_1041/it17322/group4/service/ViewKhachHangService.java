/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author DELL
 */
public interface ViewKhachHangService {

    List<ViewKhachHangRepose> getAll();

    List<ViewKhachHangRepose> getOne(UUID id);

    String add(ViewKhachHangRepose vkhr);

    String update(ViewKhachHangRepose vkhr);

    String delete(ViewKhachHangRepose vkhr);

    Account getOneNguoiTao(String id);

    List<ViewKhachHangRepose> searchByName(List<ViewKhachHangRepose> lists, String hoTen);

    List<ViewKhachHangRepose> searchByPhone(List<ViewKhachHangRepose> lists, String sdt);

    List<ViewKhachHangRepose> searchByDiaChi(List<ViewKhachHangRepose> lists, String diaChi);

    List<ViewKhachHangRepose> searchByEmail(List<ViewKhachHangRepose> lists, String email);

    List<ViewKhachHangRepose> seachKhoangNgay(String ngayBatDau, String ngayKetThuc);

    List<ViewKhachHangRepose> seachKhoangNgaySinh(String ngaySinh);

    int genMaHD();
}
