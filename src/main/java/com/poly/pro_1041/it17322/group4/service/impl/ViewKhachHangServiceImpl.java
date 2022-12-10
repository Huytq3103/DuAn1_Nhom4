/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.repository.AccountRepository;
import com.poly.pro_1041.it17322.group4.repository.KhachHangRepository;
import com.poly.pro_1041.it17322.group4.response.ViewKhachHangRepose;
import com.poly.pro_1041.it17322.group4.service.ViewKhachHangService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author DELL
 */
public class ViewKhachHangServiceImpl implements ViewKhachHangService {

    private KhachHangRepository khRepository = new KhachHangRepository();
    private AccountRepository ar = new AccountRepository();

    @Override
    public String add(ViewKhachHangRepose vkhr) {
        if (vkhr.getHoTen().trim().isEmpty()) {
            return "Họ tên đang trống";
        } else if (!vkhr.getHoTen().matches("[a-z A-Z]+")) {
            return "Họ tên là chữ";
        } else if (vkhr.getSdt().trim().isEmpty()) {
            return "Sdt đang trống";
        } else if (!vkhr.getSdt().matches("[0-9]+")) {
            return "Sdt là số";
        } else if (!vkhr.getSdt().startsWith("0")) {
            return "Sdt bắt đầu bằng 0";
        } else if (vkhr.getSdt().length() != 10) {
            return "Sdt có 10 chữ số";
        } else if (vkhr.getEmail().trim().isEmpty()) {
            return "Email đang trống";
        } else if (!vkhr.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return "Email không đúng định dạng";
        } else if (vkhr.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ đang trống";
        } else {

            KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNguoiChinhSua(), vkhr.getNgayChinhSua(), vkhr.getDiem());

            boolean add = khRepository.add(kh);
            if (add) {
                return "Add thanh cong";
            } else {
                return "Add that bai";
            }
        }
    }

    @Override
    public String update(ViewKhachHangRepose vkhr) {
        if (vkhr.getHoTen().trim().isEmpty()) {
            return "Họ tên đang trống";
        } else if (!vkhr.getHoTen().matches("[a-z A-Z]+")) {
            return "Họ tên là chữ";
        } else if (vkhr.getSdt().trim().isEmpty()) {
            return "Sdt đang trống";
        } else if (!vkhr.getSdt().matches("[0-9]+")) {
            return "Sdt là số";
        } else if (!vkhr.getSdt().startsWith("0")) {
            return "Sdt bắt đầu bằng 0";
        } else if (vkhr.getSdt().length() != 10) {
            return "Sdt có 10 chữ số";
        } else if (vkhr.getEmail().trim().isEmpty()) {
            return "Email đang trống";
        } else if (!vkhr.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            return "Email không đúng định dạng";
        } else if (vkhr.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ đang trống";
        } else {
            KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNguoiChinhSua(), vkhr.getNgayChinhSua(), vkhr.getDiem());
            boolean update = khRepository.update(kh);
            if (update) {
                return "Update thanh cong";
            } else {
                return "Update that bai";
            }
        }
    }

    @Override
    public String delete(ViewKhachHangRepose vkhr) {

        KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNguoiChinhSua(), vkhr.getNgayChinhSua(), vkhr.getDiem());

        boolean delete = khRepository.delete(kh);
        if (delete) {
            return "Delete thanh cong";
        } else {
            return "Delete that bai";
        }
    }

    @Override
    public List<ViewKhachHangRepose> getAll() {
        List<ViewKhachHangRepose> lists = new ArrayList<>();
        for (KhachHang kh : khRepository.getAll()) {
            lists.add(new ViewKhachHangRepose(kh));
        }
        return lists;
    }

    public Account getOneNguoiTao(String id) {
        return ar.getOneNguoiTao(id);
    }

    @Override
    public List<ViewKhachHangRepose> searchByName(List<ViewKhachHangRepose> lists, String hoTen) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (ViewKhachHangRepose vkh : lists) {
            if (vkh.getHoTen().contains(hoTen)) {
                list.add(vkh);
            }
        }
        return list;
    }

    @Override
    public List<ViewKhachHangRepose> getOne(UUID id) {
        return (List<ViewKhachHangRepose>) khRepository.getOne(id);
    }

    public static void main(String[] args) {
        System.out.println(new ViewKhachHangServiceImpl().getAll());
    }

    @Override
    public List<ViewKhachHangRepose> searchByPhone(List<ViewKhachHangRepose> lists, String sdt) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (ViewKhachHangRepose vkh : lists) {
            if (vkh.getSdt().contains(sdt)) {
                list.add(vkh);
            }
        }
        return list;
    }

    @Override
    public List<ViewKhachHangRepose> searchByDiaChi(List<ViewKhachHangRepose> lists, String diaChi) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (ViewKhachHangRepose vkh : lists) {
            if (vkh.getSdt().equalsIgnoreCase(diaChi)) {
                list.add(vkh);
            }
        }
        return list;
    }

    @Override
    public List<ViewKhachHangRepose> seachKhoangNgay(String ngayBatDau, String ngayKetThuc) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (KhachHang khachHang : khRepository.seachKhoangNgay(ngayBatDau, ngayKetThuc)) {
            list.add(new ViewKhachHangRepose(khachHang));
        }
        return list;
    }

    @Override
    public List<ViewKhachHangRepose> seachKhoangNgaySinh(String ngaySinh) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (KhachHang khachHang : khRepository.seachKhoangNgaySinh(ngaySinh)) {
            list.add(new ViewKhachHangRepose(khachHang));
        }
        return list;
    }

    @Override
    public int genMaHD() {
        return khRepository.genMaKH();
    }

    @Override
    public List<ViewKhachHangRepose> seachByEmail(String email) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (KhachHang khachHang : khRepository.seachByEmail(email)) {
            list.add(new ViewKhachHangRepose(khachHang));
        }
        return list;
    }

    @Override
    public ViewKhachHangRepose getOneSdt(String sdt) {
        return new ViewKhachHangRepose(khRepository.getOneSdt(sdt));
    }

    @Override
    public ViewKhachHangRepose getOneEmail(String email) {
        return new ViewKhachHangRepose(khRepository.getOneEmail(email));
    }
}
