/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.ChatLieu;
import com.poly.pro_1041.it17322.group4.domainmodel.ChiTietSanPham;
import com.poly.pro_1041.it17322.group4.domainmodel.Hang;
import com.poly.pro_1041.it17322.group4.domainmodel.KichCo;
import com.poly.pro_1041.it17322.group4.domainmodel.Loai;
import com.poly.pro_1041.it17322.group4.domainmodel.MauSac;
import com.poly.pro_1041.it17322.group4.domainmodel.SanPham;
import com.poly.pro_1041.it17322.group4.service.impl.ViewHoaDonServiceImpl;
import java.math.BigDecimal;
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
    private SanPham sp;
    private Hang hang;
    private Loai loai;
    private KichCo kichCo;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private int soLuongTon;
    private BigDecimal gia;

    public ViewCTSPResponse() {
    }

    public ViewCTSPResponse(ChiTietSanPham ctsp) {
        this.id = ctsp.getId();
        this.sp = ctsp.getSanPham();
        this.hang = ctsp.getHang();
        this.loai = ctsp.getLoai();
        this.kichCo = ctsp.getKichCo();
        this.mauSac = ctsp.getMauSac();
        this.chatLieu = ctsp.getChatLieu();
        this.soLuongTon = ctsp.getSoLuongTon();
        this.gia = ctsp.getGia();
    }

    public Object[] toDataRow() {
        return new Object[]{sp.getTenSP(), mauSac.getTen(), hang.getTen(), kichCo.getTen(), chatLieu.getTen(), loai.getTen(), soLuongTon, gia};
    }
    
    
    
    public static void main(String[] args) {
        for(ViewCTSPResponse vctspr : new ViewHoaDonServiceImpl().getAllSP()){
            System.out.println(vctspr.toString());
        }
    }
}
