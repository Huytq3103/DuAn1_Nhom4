/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.response;

import com.poly.pro_1041.it17322.group4.domainmodel.KhuyenMai;
import com.poly.pro_1041.it17322.group4.domainmodel.LoaiKM;
import com.poly.pro_1041.it17322.group4.domainmodel.TrangThaiKM;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Lenovo
 */
@Getter
@Setter
@ToString
public class ViewKhuyenMaiResponse {

    private UUID id;
    private TrangThaiKM trangThaiKM;
    private LoaiKM loaiKM;
    private String ma;
    private String ten;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Float giaKM;

    public ViewKhuyenMaiResponse() {
    }

    public ViewKhuyenMaiResponse(KhuyenMai khuyenMai) {
        this.id = khuyenMai.getId();
        this.trangThaiKM = khuyenMai.getTrangThaiKM();
        this.loaiKM = khuyenMai.getLoaiKM();
        this.ma = khuyenMai.getMa();
        this.ten = khuyenMai.getTen();
        this.ngayBatDau = khuyenMai.getNgayBatDau();
        this.ngayKetThuc = khuyenMai.getNgayKetThuc();
        this.giaKM = khuyenMai.getGiaKM();
    }

    public ViewKhuyenMaiResponse(UUID id, TrangThaiKM trangThaiKM, LoaiKM loaiKM, String ma, String ten, Date ngayBatDau, Date ngayKetThuc, Float giaKM) {
        this.id = id;
        this.trangThaiKM = trangThaiKM;
        this.loaiKM = loaiKM;
        this.ma = ma;
        this.ten = ten;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giaKM = giaKM;
    }

    public Object[] toDataRow(int i) {
        return new Object[]{i, ten, ngayBatDau, ngayKetThuc, giaKM, loaiKM.getTen()};
    }

}
