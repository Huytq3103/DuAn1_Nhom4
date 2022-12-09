/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.KhuyenMai;
import com.poly.pro_1041.it17322.group4.domainmodel.LoaiKM;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiKM;
import com.poly.pro_1041.it17322.group4.repository.ChiTietSanPhamRepository;
import com.poly.pro_1041.it17322.group4.repository.KhuyenMaiRepository;
import com.poly.pro_1041.it17322.group4.repository.LoaiKMRepository;
import com.poly.pro_1041.it17322.group4.repository.TrangThaiKMRepository;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhuyenMaiResponse;
import com.poly.pro_1041.it17322.group4.response.ViewLoaiKMResponse;
import com.poly.pro_1041.it17322.group4.response.ViewTrangThaiKMResponse;
import com.poly.pro_1041.it17322.group4.service.ViewKhuyenMaiService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Lenovo
 */
public class ViewKhuyenMaiServiceImpl implements ViewKhuyenMaiService {

    private KhuyenMaiRepository kmrp = new KhuyenMaiRepository();
    private LoaiKMRepository lkmrp = new LoaiKMRepository();
    private TrangThaiKMRepository ttkmrp = new TrangThaiKMRepository();
    private ChiTietSanPhamRepository ctsprp = new ChiTietSanPhamRepository();

    @Override
    public List<ViewKhuyenMaiResponse> getAllKM() {
        List<ViewKhuyenMaiResponse> list = new ArrayList<>();
        for (KhuyenMai khuyenMai : kmrp.getAll()) {
            list.add(new ViewKhuyenMaiResponse(khuyenMai));
        }
        return list;
    }

    @Override
    public List<ViewLoaiKMResponse> getAllLKM() {
        List<ViewLoaiKMResponse> list = new ArrayList<>();
        for (LoaiKM loaiKM : lkmrp.getAll()) {
            list.add(new ViewLoaiKMResponse(loaiKM));
        }
        return list;
    }

    @Override
    public List<ViewTrangThaiKMResponse> getAllTTKM() {
        List<ViewTrangThaiKMResponse> list = new ArrayList<>();
        for (TrangThaiKM trangThaiKM : ttkmrp.getAll()) {
            list.add(new ViewTrangThaiKMResponse(trangThaiKM));
        }
        return list;
    }

    @Override
    public List<ViewCTSPResponse> getAllSP() {
        List<ViewCTSPResponse> list = new ArrayList<>();
        for (ChiTietSanPham ctsp : ctsprp.getAll()) {
            list.add(new ViewCTSPResponse(ctsp));
        }
        return list;
    }

    @Override
    public String addKhuyenMai(ViewKhuyenMaiResponse vkmr) {

        if (kmrp.add(new KhuyenMai(null, vkmr.getTrangThaiKM(), vkmr.getLoaiKM(), vkmr.getMa(), vkmr.getTen(), vkmr.getNgayBatDau(), vkmr.getNgayKetThuc(), vkmr.getGiaKM()))) {
            return "Lưu thành công";
        } else {
            return "Add thất bại";
        }
    }

    @Override
    public boolean updateTTKhuyenMai(ViewKhuyenMaiResponse vkmr) {
        UUID id = vkmr.getId();
        KhuyenMai km = new KhuyenMai(null, vkmr.getTrangThaiKM(), vkmr.getLoaiKM(), vkmr.getMa(), vkmr.getTen(), vkmr.getNgayBatDau(), vkmr.getNgayKetThuc(), vkmr.getGiaKM());
        km.setId(id);
        if (kmrp.delete(km)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateKhuyenMai(ViewKhuyenMaiResponse vkmr) {

        if (kmrp.update(new KhuyenMai(vkmr.getId(), vkmr.getTrangThaiKM(), vkmr.getLoaiKM(), vkmr.getMa(), vkmr.getTen(), vkmr.getNgayBatDau(), vkmr.getNgayKetThuc(), vkmr.getGiaKM()))) {

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ViewKhuyenMaiResponse> getOneMaKM(String maKM) {
        return (List<ViewKhuyenMaiResponse>) kmrp.getOneMaKM(maKM);
    }

    @Override
    public List<ViewKhuyenMaiResponse> Search(String tenKM) {
        List<ViewKhuyenMaiResponse> lkm = new ArrayList<>();
        List<KhuyenMai> tenkm = new ArrayList<>();
        KhuyenMaiRepository khuyenMaiRepo = new KhuyenMaiRepository();
        for (KhuyenMai x : khuyenMaiRepo.getAll()) {
            if (x.getTen().toLowerCase().contains(tenKM.toLowerCase())) {
                lkm.add(new ViewKhuyenMaiResponse(x.getId(), x.getTrangThaiKM(), x.getLoaiKM(), x.getMa(), x.getTen(), x.getNgayBatDau(), x.getNgayKetThuc(), x.getGiaKM()));
                System.out.println(lkm + "ádsd");
            }
        }
        return lkm;
    }

    @Override
    public boolean updateCTSP(ViewCTSPResponse ctspr) {
        if (ctsprp.updatetableKM(new ChiTietSanPham(ctspr.getId(), ctspr.getSp(), ctspr.getMauSac(), ctspr.getHang(), ctspr.getKichCo(), ctspr.getChatLieu(), ctspr.getLoai(), ctspr.getKm(), ctspr.getMa(), ctspr.getSoLuongTon(), ctspr.getGia(), ctspr.getNgayNhap(), null, ctspr.getHinh(), ctspr.getTrangThai()))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ViewCTSPResponse> SearchSP(String tenSP) {
        List<ViewCTSPResponse> tsp = new ArrayList<>();
        List<ChiTietSanPham> tensp = new ArrayList<>();
        ChiTietSanPhamRepository chiTietSPRepo = new ChiTietSanPhamRepository();
        for (ChiTietSanPham x : chiTietSPRepo.getAll()) {
            if (x.getSanPham().getTenSP().toLowerCase().contains(tenSP.toLowerCase())) {
                tsp.add(new ViewCTSPResponse(x.getId(), x.getMa(), x.getSanPham(), x.getHang(), x.getLoai(), x.getKichCo(), x.getMauSac(), x.getChatLieu(), x.getNgayNhap(), x.getSoLuongTon(), x.getGia(), x.getKhuyenMai()));
                System.out.println(tsp + "ádsd");
            }
        }
        return tsp;
    }

}
