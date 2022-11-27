/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.service.ViewSanPhamService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Acer
 */
public class ViewSanPhamServiceImpl implements ViewSanPhamService {

    private ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();

    @Override
    public List<ViewCTSPResponse> getAllSP() {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ChiTietSanPham ctsp : chiTietSanPhamRepository.getAll()) {
            list.add(new ViewCTSPResponse(ctsp));
        }
        return list;
    }

    @Override
    public String add(ViewCTSPResponse ctsp) {
        if (chiTietSanPhamRepository.add(new ChiTietSanPham(null, ctsp.getSp(), ctsp.getMauSac(), ctsp.getHang(), ctsp.getKichCo(), ctsp.getChatLieu(), ctsp.getLoai(), null, ctsp.getMa(), ctsp.getSoLuongTon(), ctsp.getGia(), ctsp.getNgayNhap(), null, null))) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String update(ViewCTSPResponse ctsp, UUID id) {
        if (chiTietSanPhamRepository.update(new ChiTietSanPham(id, ctsp.getSp(), ctsp.getMauSac(), ctsp.getHang(), ctsp.getKichCo(), ctsp.getChatLieu(), ctsp.getLoai(), null, ctsp.getMa(), ctsp.getSoLuongTon(), ctsp.getGia(), ctsp.getNgayNhap(), null, null))) {
            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return chiTietSanPhamRepository.getOne(id);
    }

    @Override
    public List<ViewCTSPResponse> Search(List<ViewCTSPResponse> lists, String ma) {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ViewCTSPResponse v : lists) {
            if (v.getMa().equals(ma)) {
                list.add(v);
            }
        }
        return list;
    }

}
