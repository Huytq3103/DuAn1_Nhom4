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
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;
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
public class ViewCTSPResponse {

    private UUID id;
    private String ma;
    private SanPham sp;
    private Hang hang;
    private Loai loai;
    private KichCo kichCo;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private String ngayNhap;
    private int soLuongTon;
    private BigDecimal gia;
    private KhuyenMai km;
    
    
    public ViewCTSPResponse() {
    }

    public ViewCTSPResponse(ChiTietSanPham ctsp) {
        this.id = ctsp.getId();
        this.ma =ctsp.getMa();
        this.sp = ctsp.getSanPham();
        this.hang = ctsp.getHang();
        this.loai = ctsp.getLoai();
        this.kichCo = ctsp.getKichCo();
        this.mauSac = ctsp.getMauSac();
        this.chatLieu = ctsp.getChatLieu();
        this.ngayNhap=ctsp.getNgayNhap();
        this.soLuongTon = ctsp.getSoLuongTon();
        this.gia = ctsp.getGia();
        this.km = ctsp.getKhuyenMai();
    }

    public ViewCTSPResponse(UUID id, String ma, SanPham sp, Hang hang, Loai loai, KichCo kichCo, MauSac mauSac, ChatLieu chatLieu, String ngayNhap, int soLuongTon, BigDecimal gia, KhuyenMai km) {
        this.id = id;
        this.ma = ma;
        this.sp = sp;
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

    public ViewCTSPResponse(String ma, SanPham sp, Hang hang, Loai loai, KichCo kichCo, MauSac mauSac, ChatLieu chatLieu, String ngayNhap, int soLuongTon, BigDecimal gia) {
        this.ma = ma;
        this.sp = sp;
        this.hang = hang;
        this.loai = loai;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.ngayNhap = ngayNhap;
        this.soLuongTon = soLuongTon;
        this.gia = gia;
    }

    public ViewCTSPResponse( String ma, SanPham sp, Hang hang, Loai loai, KichCo kichCo, MauSac mauSac, ChatLieu chatLieu, String ngayNhap, int soLuongTon, BigDecimal gia, KhuyenMai km) {
        
        this.ma = ma;
        this.sp = sp;
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
        return new Object[]{sp.getTenSP(), mauSac.getTen(), hang.getTen(), kichCo.getTen(), chatLieu.getTen(), loai.getTen(), soLuongTon, gia};
    }
    public Object[] toDataRowKM() {
        return new Object[]{sp.getTenSP(),loai.getTen(),soLuongTon,false};
    }

    public Object[] toDataRow1() {
        return new Object[]{ma, sp.getTenSP(), mauSac.getTen(), hang.getTen(), kichCo.getTen(), chatLieu.getTen(), loai.getTen(), ngayNhap, soLuongTon, gia};
    }

    public static void main(String[] args) {
        for(ViewCTSPResponse vctspr : new ViewHoaDonServiceImpl().getAllSP()){
            System.out.println(vctspr.toString());
        }
    }
}
