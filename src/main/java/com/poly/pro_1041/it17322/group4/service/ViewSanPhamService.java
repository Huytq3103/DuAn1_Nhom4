/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Acer
 */
public interface ViewSanPhamService {

    List<ViewCTSPResponse> getAllSP();

    ChiTietSanPham getOne(UUID id);

    String add(ViewCTSPResponse ctsp);

    String update(ViewCTSPResponse ctsp, UUID id);

    List<ViewCTSPResponse> Search(List<ViewCTSPResponse> lists, String ten);

}
