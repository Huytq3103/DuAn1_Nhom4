/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KhuyenMai;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ViewCTSPResponse {

    private UUID id;
    private String ten;
    private String ma;
    private Hang hang;
    private Loai loai;
    private KichCo kichCo;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private String ngayNhap;
    private String ngayChinhSua;
    private int soLuongTon;
    private BigDecimal gia;
    private KhuyenMai km;
    private int trangThai;
    private String Hinh;

    public ViewCTSPResponse() {
    }

    public ViewCTSPResponse(UUID id, int soLuongTon) {
        this.id = id;
        this.soLuongTon = soLuongTon;
    }

    public ViewCTSPResponse(ChiTietSanPham ctsp) {
        this.id = ctsp.getId();
        this.ma = ctsp.getMa();
        this.hang = ctsp.getHang();
        this.loai = ctsp.getLoai();
        this.kichCo = ctsp.getKichCo();
        this.mauSac = ctsp.getMauSac();
        this.chatLieu = ctsp.getChatLieu();
        this.ngayNhap = ctsp.getNgayNhap();
        this.soLuongTon = ctsp.getSoLuongTon();
        this.gia = ctsp.getGia();
        this.km = ctsp.getKhuyenMai();
        this.ten = ctsp.getTen();
    }

    public ViewCTSPResponse(UUID id, String ma, String ten, Hang hang, Loai loai, KichCo kichCo, MauSac mauSac, ChatLieu chatLieu, String ngayNhap, int soLuongTon, BigDecimal gia, KhuyenMai km) {
        this.id = id;
        this.ma = ma;
        this.hang = hang;
        this.loai = loai;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.ngayNhap = ngayNhap;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
        this.km = km;

    }

    public ViewCTSPResponse(String ma, Hang hang, Loai loai, KichCo kichCo, MauSac mauSac, ChatLieu chatLieu, String ngayNhap, int soLuongTon, BigDecimal gia, String ten, int trangThai) {
        this.ma = ma;
        this.hang = hang;
        this.loai = loai;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.ngayNhap = ngayNhap;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public ViewCTSPResponse(String ma, Hang hang, Loai loai, KichCo kichCo, MauSac mauSac, ChatLieu chatLieu, String ngayNhap, int soLuongTon, BigDecimal gia, KhuyenMai km) {

        this.ma = ma;
        this.hang = hang;
        this.loai = loai;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.ngayNhap = ngayNhap;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
        this.km = km;
    }

    public Object[] toDataRow() {
        return new Object[]{ten, mauSac.getTen(), hang.getTen(), kichCo.getTen(), chatLieu.getTen(), loai.getTen(), soLuongTon, gia};
    }

    public Object[] toDataRowKM() {
        return new Object[]{ten, loai.getTen(), soLuongTon, false};
    }

    public Object[] toDataRow1() {
        return new Object[]{"SP" + ma, ten, mauSac.getTen(), hang.getTen(), kichCo.getTen(), chatLieu.getTen(), loai.getTen(), ngayNhap, soLuongTon, gia,km.getTen()};
    }

    public static void main(String[] args) {
        for (ViewCTSPResponse vctspr : new ViewHoaDonServiceImpl().getAllSP()) {
            System.out.println(vctspr.toString());
        }
    }
}
