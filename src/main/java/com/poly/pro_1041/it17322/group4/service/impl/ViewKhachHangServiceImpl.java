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
        if (vkhr.getMa().trim().isEmpty()) {
            return "Mã đang trống";
        } else if (!vkhr.getMa().trim().substring(0, 2).equalsIgnoreCase("KH")) {
            return "Mã bắt đầu bằng KH";
        } else if (vkhr.getHoTen().trim().isEmpty()) {
            return "Họ tên đang trống";
        } else if (!vkhr.getHoTen().matches("[a-z A-Z]+")) {
            return "Họ tên là chữ";
        } else if (vkhr.getNgaySinh().trim().isEmpty()) {
            return "Ngày sinh đang trống";
        } else if (!vkhr.getNgaySinh().matches("^([0-9]{4}[-/]?((0[13-9]|1[012])[-/]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-/]?31|02[-/]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-/]?02[-/]?29)$")) {
            return "Ngày sinh định dạng đúng là YYYYMMDD";
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
        if (vkhr.getMa().trim().isEmpty()) {
            return "Mã đang trống";
        } else if (!vkhr.getMa().substring(0, 2).equalsIgnoreCase("KH")) {
            return "Mã bắt đầu bằng KH";
        } else if (vkhr.getHoTen().trim().isEmpty()) {
            return "Họ tên đang trống";
        } else if (!vkhr.getHoTen().matches("[a-z A-Z]+")) {
            return "Họ tên là chữ";
        } else if (vkhr.getNgaySinh().trim().isEmpty()) {
            return "Ngày sinh đang trống";
        } else if (!vkhr.getNgaySinh().matches("^([0-9]{4}[-/]?((0[13-9]|1[012])[-/]?(0[1-9]|[12][0-9]|30)|(0[13578]|1[02])[-/]?31|02[-/]?(0[1-9]|1[0-9]|2[0-8]))|([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00)[-/]?02[-/]?29)$")) {
            return "Ngày sinh định dạng đúng là YYYYMMDD";
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
            if (vkh.getHoTen().equalsIgnoreCase(hoTen)) {
                list.add(vkh);
            }
        }
        return list;
    }
    
    @Override
    public List<ViewKhachHangRepose> getOne(UUID id) {
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(new ViewKhachHangServiceImpl().getAll());
    }
    
    @Override
    public List<ViewKhachHangRepose> searchByPhone(List<ViewKhachHangRepose> lists, String sdt) {
        List<ViewKhachHangRepose> list = new ArrayList<>();
        for (ViewKhachHangRepose vkh : lists) {
            if (vkh.getSdt().equalsIgnoreCase(sdt)) {
                list.add(vkh);
            }
        }
        return list;
    }
    
}
