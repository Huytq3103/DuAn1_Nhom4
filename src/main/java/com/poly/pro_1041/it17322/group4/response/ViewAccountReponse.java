/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.Account;
import com.poly.pro_1041.it17322.group4.domainmodel.ChucVuAccount;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiAccount;
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

public class ViewAccountReponse {

    private UUID id;
    private ChucVuAccount cvac;
    private TrangThaiAccount tta;
    private String hoTen;
    private String ngaySinh;
    private boolean gioiTinh;
    private String sdt;
    private String diaChi;
    private String email;
    private String username;
    private String password;
    private String ngayTao;
    private String ngayChinhSua;

    public ViewAccountReponse() {
    }

    public ViewAccountReponse(Account acc) {
        this.id = acc.getId();
        this.cvac = acc.getChucVuAccount();
        this.tta = acc.getTrangThaiAccount();
        this.hoTen = acc.getHoTen();
        this.ngaySinh = acc.getNgaySinh();
        this.gioiTinh = acc.isGioiTinh();
        this.sdt = acc.getSdt();
        this.diaChi = acc.getDiaChi();
        this.email = acc.getEmail();
        this.username = acc.getUsername();
        this.password = acc.getPassword();
        this.ngayTao = acc.getNgayTao();
        this.ngayChinhSua = acc.getNgayChinhSua();
    }

    public ViewAccountReponse(ChucVuAccount cvac, TrangThaiAccount tta, String hoTen, String ngaySinh, boolean gioiTinh, String sdt, String diaChi, String email, String username, String password, String ngayTao, String ngayChinhSua) {
        this.cvac = cvac;
        this.tta = tta;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ngayTao = ngayTao;
        this.ngayChinhSua = ngayChinhSua;
    }

    public Object[] toDataRow(int i) {
        return new Object[]{i, hoTen, ngaySinh, sdt, email, ngayTao, diaChi, cvac.getTen(), gioiTinh == true ? "Nam" : "Nữ", tta.getId()};
    }
}
