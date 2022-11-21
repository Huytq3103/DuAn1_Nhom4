/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.domainmodel;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPham {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSP", referencedColumnName = "Id")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMauSac", referencedColumnName = "Id")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHang", referencedColumnName = "Id")
    private Hang hang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKichCo", referencedColumnName = "Id")
    private KichCo kichCo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdChatLieu", referencedColumnName = "Id")
    private ChatLieu chatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdLoai", referencedColumnName = "Id")
    private Loai loai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdKM", referencedColumnName = "Id")
    private KhuyenMai khuyenMai;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "Gia")
    private BigDecimal gia;

    @Column(name = "NgayNhap")
    private String ngayNhap;

    @Column(name = "NgayChinhSua")
    private String ngayChinhSua;
    
    @Column(name = "Hinh")
    private String Hinh;
}
