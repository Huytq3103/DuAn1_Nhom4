/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.pro_1041.it17322.group4.domainmodel;

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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Huy PC
 */
@Entity
@Table(name = "KhuyenMai")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMai {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTT", referencedColumnName = "Id")
    private TrangThaiKM trangThaiKM;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdLoai", referencedColumnName = "Id")
    private LoaiKM loaiKM;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "NgayBatDau")
    private String ngayBatDau;

    @Column(name = "NgayKetThuc")
    private String ngayKetThuc;

    @Column(name = "GiaKM")
    private Float giaKM;

    @Column(name = "LoaiSanPham")
    private int loaiSanPham;
}
