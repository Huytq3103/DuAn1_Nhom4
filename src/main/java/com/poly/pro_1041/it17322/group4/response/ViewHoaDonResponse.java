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
    private String ngaoTao;
    private String ngayThanhToan;
    private BigDecimal TongTien;

    public ViewHoaDonResponse() {
    }

    public ViewHoaDonResponse(HoaDon hd) {
        this.id = hd.getId();
        this.account = hd.getAccount();
        this.khachHang = hd.getKhachHang();
        this.tto = hd.getTrangThaiOrder();
        this.ngaoTao = hd.getNgayTao();
        this.ngayThanhToan = hd.getNgayThanhToan();
        this.TongTien = hd.getTongTien();
    }

    public Object[] toDataRow() {
        return new Object[]{account.getHoTen(), " ", tto.getTen(), ngaoTao, ngayThanhToan, TongTien};
    }

}
