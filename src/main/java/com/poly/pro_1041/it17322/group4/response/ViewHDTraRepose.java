/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDonTra;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author DELL
 */
@Getter
@Setter
@ToString
public class ViewHDTraRepose {

    private UUID id;
    private KhachHang khachHang;
    private Account account;
    private String ngayTao;
    private boolean hinhThuc;
    private BigDecimal tongTien;

    public ViewHDTraRepose() {
    }

    public ViewHDTraRepose(HoaDonTra hdt) {
        this.id = hdt.getId();
        this.khachHang = hdt.getKhachHang();
        this.account = hdt.getAccount();
        this.ngayTao = hdt.getNgayTao();
        this.hinhThuc = hdt.isHinhThuc();
        this.tongTien = hdt.getTongTien();
    }

    public ViewHDTraRepose(KhachHang khachHang, Account account, String ngayTao, boolean hinhThuc, BigDecimal tongTien) {
        this.khachHang = khachHang;
        this.account = account;
        this.ngayTao = ngayTao;
        this.hinhThuc = hinhThuc;
        this.tongTien = tongTien;
    }

    public Object[] toDataRow(int stt) {
        return new Object[]{stt, id, khachHang.getHoTen(), account.getUsername(), ngayTao, hinhThuc, tongTien};
    }
}
