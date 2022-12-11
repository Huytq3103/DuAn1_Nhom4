/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service.impl;

import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.repository.LoaiSPRepository;
import com.poly.pro_1041.it17322.group4.service.ViewLoaiService;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ViewLoaiServiceImpl implements ViewLoaiService {

    private LoaiSPRepository loaiSPRepository = new LoaiSPRepository();

    @Override
    public List<Loai> getAll() {
        return loaiSPRepository.getAll();
    }

    @Override
    public Loai getOneTen(String ten) {
        return loaiSPRepository.getOneTen(ten);
    }

}
