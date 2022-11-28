/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.KhachHang;
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
//@NoArgsConstructor
//@AllArgsConstructor
public class ViewKhachHangRepose {

    private UUID id;
    private String ma;
    private String hoTen;
    private String ngaySinh;
    private boolean gioiTinh;
    private String sdt;
    private String email;
    private String diaChi;
    private String ngayTao;
    private String ngayChinhSua;
    private UUID nguoiTao;
    private UUID nguoiChinhSua;

    public ViewKhachHangRepose() {
    }

    public ViewKhachHangRepose(KhachHang kh) {
        this.id = kh.getId();
        this.ma = kh.getMaKH();
        this.hoTen = kh.getHoTen();
        this.ngaySinh = kh.getNgaySinh();
        this.gioiTinh = kh.isGioiTinh();
        this.sdt = kh.getSdt();
        this.email = kh.getEmail();
        this.diaChi = kh.getDiaChi();
        this.ngayTao = kh.getNgayTao();
        this.ngayChinhSua = kh.getNgayChinhSua();
        this.nguoiTao = kh.getNguoiTao();
        this.nguoiChinhSua = kh.getNguoiChinhSua();
    }

    public ViewKhachHangRepose(String ma, String hoTen, String ngaySinh, boolean gioiTinh, String sdt, String email, String diaChi, String ngayTao, String ngayChinhSua, UUID nguoiTao, UUID nguoiChinhSua) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
        this.ngayChinhSua = ngayChinhSua;
        this.nguoiTao = nguoiTao;
        this.nguoiChinhSua = nguoiChinhSua;
    }

    public Object[] toDataRow() {
        return new Object[]{ma, hoTen, ngaySinh, gioiTinh == true ? "Nam" : "Nữ", sdt, email, diaChi, ngayTao, ngayChinhSua};
    }
}
