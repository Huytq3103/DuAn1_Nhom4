/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.service;

import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface ViewThuocTinhService {

    List<SanPham> getAllSanPham();

    List<MauSac> getAllMauSac();

    List<Loai> getAllLoai();

    List<KichCo> getAllKichCo();

    List<Hang> getAllHang();

    List<ChatLieu> getAllChatLieu();

    String AddSanPham(SanPham sanPham);

    String AddMauSac(MauSac mauSac);

    String AddLoai(Loai loai);

    String AddKichCo(KichCo kichCo);

    String AddHang(Hang hang);

    String AddChatLieu(ChatLieu chatLieu);

    String UpdateMauSac(MauSac mauSac);

    String UpdateSanPham(SanPham sanPham);

    String UpdateLoai(Loai loai);

    String UpdateKichCo(KichCo kichCo);

    String UpdateHang(Hang hang);

    String UpdateChatLieu(ChatLieu chatLieu);
}
