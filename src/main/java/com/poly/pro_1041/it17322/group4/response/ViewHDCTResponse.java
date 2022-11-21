/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonChiTiet;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Huy PC
 */
@Getter
@Setter
@ToString
public class ViewHDCTResponse {

    private HoaDon hd;
    private ChiTietSanPham ctsp;
    private String hang;
    private String ten;
    private String mauSac;
    private String loai;
    private String kichCo;
    private String chatLieu;
    private int soLuong;
    private BigDecimal gia;

    public ViewHDCTResponse() {
    }

    public ViewHDCTResponse(HoaDonChiTiet hdct) {
        this.hd = hdct.getHoaDon();
        this.ctsp = hdct.getChiTietSanPham();
        this.hang = hdct.getHang();
        this.ten = hdct.getTenSP();
        this.mauSac = hdct.getMauSac();
        this.loai = hdct.getLoai();
        this.kichCo = hdct.getKichCo();
        this.chatLieu = hdct.getChatLieu();
        this.soLuong = hdct.getSoLuong();
        this.gia = hdct.getDonGia();
    }

    public Object[] toDataRow() {
        return new Object[]{ctsp.getSanPham().getTenSP(), mauSac, hang, kichCo, chatLieu, loai, soLuong, gia};
    }
}
