/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
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

    @Override
    public KhachHang getOne(UUID id) {
        return khRepository.getOne(id);
    }

    @Override
    public String add(ViewKhachHangRepose vkhr) {
        KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(),vkhr.getNgayChinhSua(), vkhr.getNguoiChinhSua());
        boolean add = khRepository.add(kh);
        if (add) {
            return "Add thanh cong";
        } else {
            return "Add that bai";
        }
    }

    @Override
    public String update(ViewKhachHangRepose vkhr) {
        KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNgayChinhSua(), vkhr.getNguoiChinhSua());
        boolean update = khRepository.update(kh);
        if (update) {
            return "Update thanh cong";
        } else {
            return "Update that bai";
        }
    }

    @Override
    public String delete(ViewKhachHangRepose vkhr) {
        KhachHang kh = new KhachHang(vkhr.getId(), vkhr.getMa(), vkhr.getHoTen(), vkhr.getNgaySinh(), vkhr.isGioiTinh(), vkhr.getSdt(), vkhr.getDiaChi(), vkhr.getEmail(), vkhr.getNgayTao(), vkhr.getNguoiTao(), vkhr.getNgayChinhSua(), vkhr.getNguoiChinhSua());
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

    public static void main(String[] args) {
        new ViewKhachHangServiceImpl().getAll().forEach(s -> System.out.println(s.toString()));
    }

}
