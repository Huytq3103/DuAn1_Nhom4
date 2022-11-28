/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonChiTietRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonRepository;
import com.poly.pro_1041.it17322.group4.repository.ThongKeRepository;
import com.poly.pro_1041.it17322.group4.response.ViewThongKeResponse;
import com.poly.pro_1041.it17322.group4.service.ViewThongKeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ViewThongKeServiceImpl implements ViewThongKeService {

    private HoaDonRepository hdr = new HoaDonRepository();
    private HoaDonChiTietRepository hdctr = new HoaDonChiTietRepository();
    private ThongKeRepository tkr = new ThongKeRepository();
    private ChiTietSanPhamRepository ctspr = new ChiTietSanPhamRepository();

    @Override
    public Integer getTongDonHang() {
        return tkr.getTongDonHang();
    }

    @Override
    public List<Integer> getNam() {
        return tkr.getNam();
    }

    @Override
    public BigDecimal getTongDoanhThuThang() {
        return tkr.getTongDoanhThuThang();
    }

    @Override
    public BigDecimal getTongDoanhThuNam() {
        return tkr.getTongDoanhThuNam();
    }

    @Override
    public BigDecimal getTongDoanhThuNgay(String ngay) {
        return tkr.getTongDoanhThuNgay(ngay);
    }

    @Override
    public Integer getHoaDonThanhCong() {
        return tkr.getHoaDonThanhCong();
    }

    @Override
    public Integer getHoaDonBiHuy() {
        return tkr.getHoaDonBiHuy();
    }

    @Override
    public List<BigDecimal> getGiaBan(int nam) {
        return tkr.getGiaBan(nam);
    }

    @Override
    public List<Integer> getThang(int nam) {
        return tkr.getThang(nam);
    }

    @Override
    public BigDecimal getTongDoanhThuNgay2(String ngayBatDau, String ngayKetThuc) {
        return tkr.getTongDoanhThuNgay2(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<ViewThongKeResponse> getChiTietSP() {
        List<ViewThongKeResponse> lists = new ArrayList<>();
        for (HoaDonChiTiet i : hdctr.getAll()) {
            lists.add(new ViewThongKeResponse(i));
        }
        return lists;
    }

}
