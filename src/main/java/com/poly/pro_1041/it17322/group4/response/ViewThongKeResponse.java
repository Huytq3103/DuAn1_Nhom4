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
 * @author Dell
 */
@Getter
@Setter
@ToString
public class ViewThongKeResponse {

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

    public ViewThongKeResponse() {
    }

    public ViewThongKeResponse(HoaDonChiTiet hdct) {
        this.ctsp = hdct.getChiTietSanPham();
        this.hang = hdct.getHang();
        this.ten = hdct.getTenSP();
        this.mauSac = hdct.getMauSac();
        this.loai = hdct.getLoai();
        this.kichCo = hdct.getKichCo();
        this.chatLieu = hdct.getChatLieu();
        this.soLuong = hdct.getSoLuong();
    }

    public ViewThongKeResponse(ViewCTSPResponse vctsp) {
        ChiTietSanPham ctsp = new ChiTietSanPham();
        ctsp.setId(vctsp.getId());
        ctsp.setMa(vctsp.getMa());
        this.ctsp = ctsp;
        this.hang = vctsp.getHang().getTen();
        this.ten = vctsp.getTen();
        this.mauSac = vctsp.getMauSac().getTen();
        this.loai = vctsp.getLoai().getTen();
        this.kichCo = vctsp.getKichCo().getTen();
        this.chatLieu = vctsp.getChatLieu().getTen();
        this.soLuong = vctsp.getSoLuongTon();
        this.gia = vctsp.getGia();
    }

    public Object[] toDataRow(int i) {
        return new Object[]{i, ctsp.getMa(), ten, mauSac, hang, chatLieu, kichCo, loai, soLuong};
    }
}
