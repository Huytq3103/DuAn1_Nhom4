/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.response.ViewThongKeResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ViewThongKeService {

    Integer getTongDonHang();

    Integer getHoaDonThanhCong();

    Integer getHoaDonBiHuy();

    List<Integer> getNam();

    BigDecimal getTongDoanhThuThang();

    BigDecimal getTongDoanhThuNam();

    BigDecimal getTongDoanhThuNgay(String ngay);

    List<BigDecimal> getGiaBan(int nam);

    List<Integer> getThang(int nam);

    BigDecimal getTongDoanhThuNgay2(String ngayBatDau, String ngayKetThuc);

    List<ViewThongKeResponse> getChiTietSP();

}
