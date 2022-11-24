/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonChiTietRepository;
import com.poly.pro_1041.it17322.group4.repository.HoaDonRepository;
import com.poly.pro_1041.it17322.group4.repository.TrangThaiOrderRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHDCTResponse;
import com.poly.pro_1041.it17322.group4.response.ViewHoaDonResponse;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JTextField;

/**
 *
 * @author Huy PC
 */
public class ViewHoaDonServiceImpl implements ViewHoaDonService {

    private ChiTietSanPhamRepository ctspr = new ChiTietSanPhamRepository();
    private HoaDonRepository hdr = new HoaDonRepository();
    private HoaDonChiTietRepository hdctr = new HoaDonChiTietRepository();
    private TrangThaiOrderRepository ttor = new TrangThaiOrderRepository();
    private AccountRepository ar = new AccountRepository();
    private String regexInt = "[0-9]+";

    @Override
    public List<ViewCTSPResponse> getAllSP() {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ChiTietSanPham ctsp : ctspr.getAll()) {
            list.add(new ViewCTSPResponse(ctsp));
        }
        return list;
    }

    @Override
    public List<ViewHoaDonResponse> getAllHD() {
        List<ViewHoaDonResponse> list = new ArrayList<>();
        for (HoaDon hd : hdr.getAll()) {
            list.add(new ViewHoaDonResponse(hd));
        }
        return list;
    }

    @Override
    public List<ViewHDCTResponse> getAllHDCT() {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getAll()) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }

    @Override
    public List<ViewHDCTResponse> getOneHD(UUID id) {
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getOneHD(id)) {
            list.add(new ViewHDCTResponse(hdct));
        }
        return list;
    }

    @Override
    public List<TrangThaiOrder> getAllTTO() {
        return ttor.getAll();
    }

    @Override
    public String thanhToan(BigDecimal tongTien, JTextField tienKhachDua, ViewHoaDonResponse vhdr) {
        if (tienKhachDua.getText().trim() == null) {
            return "Không được để trống";
        } else if (!tienKhachDua.getText().trim().matches(regexInt)) {
            return "Tiền đưa phải là số";
        } else if (Double.valueOf(String.valueOf(tongTien)) > Double.valueOf(tienKhachDua.getText())) {
            return "Tiền khách đưa không đủ";
        } else {
            if (hdr.update(new HoaDon(vhdr.getId(), vhdr.getAccount(), vhdr.getKhachHang(), new TrangThaiOrder(1, "TTO1", "Đã TT"), vhdr.getTen(), vhdr.getNgaoTao(), vhdr.getNgayThanhToan(), null, null, vhdr.getTongTien(), null, null))) {
                return "Thanh toán thành công";
            } else {
                return "Thanh toán thất bại";
            }
        }
    }

    @Override
    public String tienThua(BigDecimal tongTien, JTextField tienKhachDua) {
        if (tienKhachDua.getText().trim() == null) {
            return "0";
        } else if (!tienKhachDua.getText().trim().matches(regexInt)) {
            return "0";
        } else {
            return String.valueOf(Double.valueOf(tienKhachDua.getText()) - Double.valueOf(String.valueOf(tongTien)));
        }
    }

    @Override
    public String addHoaDon(ViewHoaDonResponse vhdr) {
        if (hdr.add(new HoaDon(null, vhdr.getAccount(), vhdr.getKhachHang(), vhdr.getTto(), vhdr.getTen(), vhdr.getNgaoTao(), null, null, null, vhdr.getTongTien(), null, null))) {
            return "Add thành công";
        } else {
            return "Add thất bại";
        }
    }

    @Override
    public boolean addHDCT(ViewHDCTResponse vhdctr, UUID id) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(vhdctr.getCtsp().getId());
        HoaDon hd = new HoaDon();
        hd.setId(id);
        HoaDonChiTiet hdct = new HoaDonChiTiet(ctsp, hd, vhdctr.getTen(), vhdctr.getMauSac(), vhdctr.getHang(), vhdctr.getLoai(), vhdctr.getKichCo(), vhdctr.getChatLieu(), vhdctr.getSoLuong(), vhdctr.getGia());
        if (hdctr.add(hdct)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public ViewHDCTResponse getOneHDCT(UUID idHD, UUID idCTSP) {
        return new ViewHDCTResponse(hdctr.getOneHDCT(idHD, idCTSP));
    }

    @Override
    public boolean updateHDCT(ViewHDCTResponse vhdctr) {
        if (hdctr.update(new HoaDonChiTiet(vhdctr.getCtsp(), vhdctr.getHd(), vhdctr.getTen(), vhdctr.getMauSac(), vhdctr.getHang(), vhdctr.getLoai(), vhdctr.getKichCo(), vhdctr.getChatLieu(), vhdctr.getSoLuong(), vhdctr.getGia()))) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public BigDecimal getTongTienHD(UUID id) {
        Double tongTien = 0.0;
        List<ViewHDCTResponse> list = new ArrayList<>();
        for (HoaDonChiTiet hdct : hdctr.getOneHD(id)) {
            tongTien += Double.valueOf(String.valueOf(hdct.getDonGia())) * hdct.getSoLuong();
        }
        return BigDecimal.valueOf(tongTien);
    }

    @Override
    public boolean updateHD(ViewHoaDonResponse vhdr) {
        if (hdr.update(new HoaDon(vhdr.getId(), vhdr.getAccount(), vhdr.getKhachHang(), vhdr.getTto(), vhdr.getTen(), vhdr.getNgaoTao(), vhdr.getNgayThanhToan(), null, null, vhdr.getTongTien(), null, null))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteHDCT(ViewHDCTResponse vhdctr) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHoaDon(vhdctr.getHd());
        hdct.setChiTietSanPham(vhdctr.getCtsp());
        if (hdctr.delete(hdct)) {
            return true;
        } else {
            return false;
        }
    }
}
