/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonTra;
import com.poly.pro_1041.it17322.group4.repository.HoaDonTraRepository;
import com.poly.pro_1041.it17322.group4.response.ViewHDTraRepose;
import com.poly.pro_1041.it17322.group4.service.ViewHoaDonTraService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ViewHoaDonTraServiceImpl implements ViewHoaDonTraService {

    private HoaDonTraRepository hdtr = new HoaDonTraRepository();

    @Override
    public List<ViewHDTraRepose> getAll() {
        List<ViewHDTraRepose> lists = new ArrayList<>();
        for (HoaDonTra hdt : hdtr.getAll()) {
            lists.add(new ViewHDTraRepose(hdt));
        }
        return lists;
    }

    @Override
    public String add(ViewHDTraRepose vhdt) {
        HoaDonTra hdt = new HoaDonTra(vhdt.getId(), vhdt.getAccount(), vhdt.getKhachHang(), vhdt.getNgayTao(), vhdt.isHinhThuc(), vhdt.getTongTien());
        boolean add = hdtr.add(hdt);
        if (add) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(ViewHDTraRepose vhdt) {
        HoaDonTra hdt = new HoaDonTra(vhdt.getId(), vhdt.getAccount(), vhdt.getKhachHang(), vhdt.getNgayTao(), vhdt.isHinhThuc(), vhdt.getTongTien());
        boolean update = hdtr.update(hdt);
        if (update) {
            return "Cập nhật thành công";
        } else {
            return "Cập nhật thất bại";
        }
    }

}
