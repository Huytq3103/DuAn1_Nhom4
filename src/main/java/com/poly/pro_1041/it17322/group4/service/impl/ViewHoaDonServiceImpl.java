/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonChiTietRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Huy PC
 */
public class ViewHoaDonServiceImpl implements ViewHoaDonService {

    private ChiTietSanPhamRepository ctspr = new ChiTietSanPhamRepository();
    private HoaDonRepository hdr = new HoaDonRepository();
    private HoaDonChiTietRepository hdctr = new HoaDonChiTietRepository();

    @Override
    public List<ViewCTSPResponse> getAllSP() {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ChiTietSanPham ctsp : ctspr.getAll()) {
            list.add(new ViewCTSPResponse(ctsp));
        }
        return list;
    }

    public static void main(String[] args) {
        for (ViewHoaDonResponse vhdr : new ViewHoaDonServiceImpl().getAllHD()) {
            System.out.println(vhdr.toString());
        }
    }

    @Override
    public List<ViewHoaDonResponse> getAllHD() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAll()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    public List<ViewHDCTResponse> getAllHDCT() {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getAll()) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }

    public List<ViewHDCTResponse> getOneHD(UUID id) {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getOneHD(id)) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }
}
