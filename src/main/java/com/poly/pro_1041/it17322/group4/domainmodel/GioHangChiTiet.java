/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.domainmodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Huy PC
 */
@Entity
@Table(name = "GioHangChiTiet")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTiet implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCTSP", referencedColumnName = "Id")
    private ChiTietSanPham chiTietSanPham;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdGioHang", referencedColumnName = "Id")
    private GioHang gioHang;

    @Column(name = "TenSP")
    private String tenSP;

    @Column(name = "MauSac")
    private String mauSac;

    @Column(name = "Hang")
    private String hang;

    @Column(name = "Loai")
    private String loai;

    @Column(name = "KichCo")
    private String kichCo;

    @Column(name = "ChatLieu")
    private String chatLieu;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "DonGia")
    private String donGia;
}
