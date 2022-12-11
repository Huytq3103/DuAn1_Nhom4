/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonChiTietRepository;
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
    private HoaDonChiTietRepository hdctr = new HoaDonChiTietRepository();

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
        ChiTietSanPham spMa = null;

        if (chiTietSanPhamRepository.add(new ChiTietSanPham(null, ctsp.getMauSac(), ctsp.getHang(), ctsp.getKichCo(), ctsp.getChatLieu(), ctsp.getLoai(), null, "" + chiTietSanPhamRepository.genMaCTSP(), ctsp.getTen(), ctsp.getSoLuongTon(), ctsp.getGia(), ctsp.getNgayNhap(), null, null, ctsp.getTrangThai()))) {

            return "Thành công";
        } else {
            return "Không thành công";
        }
    }

    @Override
    public String update(ViewCTSPResponse ctsp, UUID id) {
        if (chiTietSanPhamRepository.update(new ChiTietSanPham(id, ctsp.getMauSac(), ctsp.getHang(), ctsp.getKichCo(), ctsp.getChatLieu(), ctsp.getLoai(), ctsp.getKm(), ctsp.getMa(), ctsp.getTen(), ctsp.getSoLuongTon(), ctsp.getGia(), ctsp.getNgayNhap(), ctsp.getNgayChinhSua(), ctsp.getHinh(), ctsp.getTrangThai()))) {
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
    public List<ViewCTSPResponse> Search(List<ViewCTSPResponse> lists, String ten) {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ViewCTSPResponse v : lists) {
            if (v.getTen().equals(ten)) {
                list.add(v);
            }
        }
        return list;
    }

    @Override
    public boolean updateSoLuongTonKhiThem(ViewCTSPResponse vctsp) {
        boolean check = true;
        HoaDonChiTiet hdct = hdctr.getOneUpdateHoaDon(vctsp.getId());
        int soLuongMoi = hdct.getSoLuong() - vctsp.getSoLuongTon();
        hdct.setSoLuong(soLuongMoi);
        if (hdctr.updateTableHD(hdct)) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    @Override
    public boolean checkSoLuongGioHangVoiSoLuongSP(ViewCTSPResponse vctspr) {
        boolean check = true;
        int soluong = hdctr.getOne(vctspr.getId()).getSoLuong();
        if (vctspr.getSoLuongTon() < soluong) {
            check = false;
        }
        return true;
    }
}
