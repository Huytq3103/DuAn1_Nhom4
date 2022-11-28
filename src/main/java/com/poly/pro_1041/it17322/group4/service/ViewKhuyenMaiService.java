/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.response.ViewCTSPResponse;
import com.poly.pro_1041.it17322.group4.response.ViewKhuyenMaiResponse;
import com.poly.pro_1041.it17322.group4.response.ViewLoaiKMResponse;
import com.poly.pro_1041.it17322.group4.response.ViewTrangThaiKMResponse;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Lenovo
 */
public interface ViewKhuyenMaiService {
    
    List<ViewKhuyenMaiResponse> getAllKM();
    
    List<ViewLoaiKMResponse> getAllLKM();
    
    List<ViewTrangThaiKMResponse> getAllTTKM();
    
    List<ViewCTSPResponse> getAllSP();
    
    List<ViewCTSPResponse> getOneLoai(int idLoai);
    
    public String addKhuyenMai(ViewKhuyenMaiResponse vkmr);
    
    boolean deleteKhuyenMai(ViewKhuyenMaiResponse vkmr);
    
    boolean updateKhuyenMai(ViewKhuyenMaiResponse vkmr);
}