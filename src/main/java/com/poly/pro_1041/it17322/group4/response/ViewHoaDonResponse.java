/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.HoaDon;
import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiOrder;
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
public class ViewHoaDonResponse {

    private UUID id;
    private Account account;
    private KhachHang khachHang;
    private TrangThaiOrder tto;
    private String ma;
    private String ngaoTao;
    private String ngayThanhToan;
    private String ngayShip;
    private String ngayNhan;
    private BigDecimal TongTien;

    public ViewHoaDonResponse() {
    }

    public ViewHoaDonResponse(HoaDon hd) {
        this.id = hd.getId();
        this.account = hd.getAccount();
        this.khachHang = hd.getKhachHang();
        this.tto = hd.getTrangThaiOrder();
        this.ma = hd.getMa();
        this.ngaoTao = hd.getNgayTao();
        this.ngayThanhToan = hd.getNgayThanhToan();
        this.TongTien = hd.getTongTien();
    }

    public ViewHoaDonResponse(UUID id, Account account, KhachHang khachHang, TrangThaiOrder tto, String ma, String ngaoTao, String ngayThanhToan, BigDecimal TongTien) {
        this.id = id;
        this.account = account;
        this.khachHang = khachHang;
        this.tto = tto;
        this.ma = ma;
        this.ngaoTao = ngaoTao;
        this.ngayThanhToan = ngayThanhToan;
        this.TongTien = TongTien;
    }

    public ViewHoaDonResponse(UUID id, Account account, KhachHang khachHang, TrangThaiOrder tto, String ngaoTao, String ngayThanhToan, BigDecimal TongTien) {
        this.id = id;
        this.account = account;
        this.khachHang = khachHang;
        this.tto = tto;
        this.ngaoTao = ngaoTao;
        this.ngayThanhToan = ngayThanhToan;
        this.TongTien = TongTien;
    }

    public ViewHoaDonResponse(Account account, String ten, String ngaoTao, String ngayThanhToan, String ngayShip, String ngayNhan, BigDecimal TongTien) {
        this.account = account;
        this.ma = ten;
        this.ngaoTao = ngaoTao;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.TongTien = TongTien;
    }

    public Object[] toDataRow() {
        return new Object[]{account.getHoTen(), khachHang == null ? " " : khachHang.getMaKH(), ma, tto.getTen(), ngaoTao, ngayThanhToan, TongTien};
    }

    public Object[] toDataRowLS(int k) {
        return new Object[]{k + 1, account.getHoTen(), khachHang == null ? " " : khachHang.getMaKH(), ma, tto.getTen(), ngaoTao, ngayThanhToan, TongTien};
    }

    public Object[] toDataRowHD(int stt) {
        return new Object[]{stt, ma, ngaoTao, ngayThanhToan, ngayShip, ngayNhan, TongTien};
    }
}
